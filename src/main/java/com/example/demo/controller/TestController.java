package com.example.demo.controller;

import com.example.demo.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sayHello")
    public Result test(@RequestParam("name") String name){
        return Result.ok("hello: " + name);
    }
}
