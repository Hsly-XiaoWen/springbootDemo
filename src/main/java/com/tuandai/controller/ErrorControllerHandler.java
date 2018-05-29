package com.tuandai.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 肖文 on 2018/5/29
 */
@RestControllerAdvice(value = "com.tuandai.controller")
public class ErrorControllerHandler {

    @ExceptionHandler(value = Exception.class)
    public String errorHandler(HttpServletRequest request
            , HttpServletResponse response, Exception e)throws Exception {
        e.printStackTrace();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(request.getRequestURL());
        stringBuilder.append("请求失败:");
        stringBuilder.append(e);
        return stringBuilder.toString();
    }
}
