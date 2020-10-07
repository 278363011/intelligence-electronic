package com.sydl.console.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TestController {


    @RequestMapping("/hello")
    public void hello(){
        log.error("我是错误信息");
        log.info("我是正常信息");
        System.out.println(1/0);
    }




}
