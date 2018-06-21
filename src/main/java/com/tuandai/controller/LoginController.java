package com.tuandai.controller;

import com.tuandai.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 肖文 on 2018/6/21
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtConfig jwtConfig;

    @ApiOperation("生成token")
    @GetMapping("/check")
    public String checkUser(@RequestParam("userName") String username,
                            @RequestParam("password") String password) {
        String token = jwtConfig.createJWT(username);
        System.out.println(token+"=============");
        return "登录成功 "+token;
    }

    @ApiOperation("校验jwt token")
    @GetMapping("/checks")
    public String checkToken(@RequestParam("token") String token) {
        Claims result = jwtConfig.checkJWT(token);
        if (result != null) {
            return "校验成功";
        } else {
            return "token校验失败";
        }
    }
}
