package com.ccda.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//请求处理类
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        String greet = "Helloo world!!!";
        System.out.println(greet);
        return greet;
    }

    @RequestMapping("/hellolist")
    public List<String> helloList(){
        return List.of("Hi","guys!");
    }
}



