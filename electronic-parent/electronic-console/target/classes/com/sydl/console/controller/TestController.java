package com.sydl.console.controller;

import com.sydl.console.model.NewsMessage;
import com.sydl.console.result.Result;
import com.sydl.console.service.NewsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class TestController {

@Autowired
    NewsMessageService newsMessageService;


    @RequestMapping("/hello")
    public Result<List<NewsMessage>> hello(){
        log.error("我是错误信息");
        log.info("我是正常信息");
        List<NewsMessage> list = newsMessageService.list();
        return Result.success(list);
    }




}
