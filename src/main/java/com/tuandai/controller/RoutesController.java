package com.tuandai.controller;

import com.alibaba.fastjson.JSON;
import com.tuandai.SpringBootDemoUrl;
import com.tuandai.dto.Result;
import com.tuandai.entiy.Routes;
import com.tuandai.entiy.User;
import com.tuandai.service.RoutesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 肖文 on 2018/5/6
 */
@RestController
@RequestMapping(SpringBootDemoUrl.ROUTES)
public class RoutesController {

    @Autowired
    private RoutesService routesService;
    @Autowired
    private Routes route;

    @ApiOperation("测试")
    @PostMapping("/test")
    public Result getResult() {
        return Result.success(route);
    }

    @ApiOperation("获取接口路由数据")
    @GetMapping(SpringBootDemoUrl.ROUTES_LIST)
    public String getRoutes() {
        return JSON.toJSONString(this.routesService.getRoutes());
    }

    @ApiOperation("JSON格式显示")
    @GetMapping(SpringBootDemoUrl.USER)
    public User getUser(){
        return new User(1, "www", 11, null);
    }

    @ApiOperation("JSON格式显示")
    @GetMapping(SpringBootDemoUrl.USER1)
    public User getUser1(){
        return new User(1, "www", 11, "SFS");
    }
}
