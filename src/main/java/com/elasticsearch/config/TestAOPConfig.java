package com.elasticsearch.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//表示切入点
@Aspect
@Component
public class TestAOPConfig {

    //定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码。
    //使用 @Pointcut 来声明切入点表达式。
    //后面的其他通知直接使用方法名来引用当前的切入点表达式。
    //（..）表示任意参数
    @Pointcut("execution(public * com.elasticsearch.action..*.*(..))")
    public void testAop() {
    }

    //前置通知：在方法执行前通知
    @Before("testAop()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println(String.format("@Before通知：beforeMethod方法名称：%s，方法入参：%s", methodName, joinPoint.getArgs()));
    }

    //方法执行后通知： 在目标方法执行后无论是否发生异常，执行通知,不能访问目标方法的执行的结果。
    @After("testAop()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(String.format("@After通知：afterMethod方法名称：%s", methodName));
    }

    //后置通知：在方法正常执行完成进行通知，可以访问到方法的返回值的。
    @AfterReturning(value = "testAop()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(String.format("@AfterReturning通知：afterReturning方法名称：%s，方法出参：%s", methodName, result));
    }

    //异常通知：在方法出现异常时进行通知,可以访问到异常对象，且可以指定在出现特定异常时在执行通知。
    @AfterThrowing(value = "testAop()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(String.format("@AfterThrowing通知：afterThrowing方法名称：%s，异常参数：%s", methodName, e));
    }

    //环绕通知：可以将要执行的方法（point.proceed()）进行包裹执行，可以在前后添加需要执行的操作
    @Around("testAop()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around通知：环绕通知开始，方法入参：" + point.getArgs());
        Object result = point.proceed();
        System.out.println("@Around通知：环绕通知结束，方法返回参数：" + result);
        return result;
    }
}
