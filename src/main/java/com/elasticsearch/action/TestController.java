package com.elasticsearch.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    @RequestMapping("/index")
    public String index() throws Exception {
        System.out.println("控制器内部方法执行");
        TimeUnit.SECONDS.sleep(1);
        //int a = 1 / 0;
        return "返回页面";
    }
}
