package com.sydl.console.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyException(MyException ex){
        System.out.println("全局异常捕捉");
        if(ex instanceof  MyException){
            System.out.println("触发我的异常");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("message", "触发我的异常");
        return result;
    }
}
