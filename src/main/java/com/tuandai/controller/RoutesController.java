package com.tuandai.controller;

import com.alibaba.fastjson.JSON;
import com.tuandai.SpringBootDemoUrl;
import com.tuandai.service.RoutesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation("获取接口路由数据")
    @GetMapping(SpringBootDemoUrl.ROUTES_LIST)
    public String getRoutes(){
        return JSON.toJSONString(this.routesService.getRoutes());
    }
}
