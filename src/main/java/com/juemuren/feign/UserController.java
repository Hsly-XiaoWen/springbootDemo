package com.juemuren.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 肖文 on 2018/6/19
 */
@RestController
@RequestMapping("/use")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @PostMapping("/find/getName")
    public List<Map> find() {
        System.out.println(userService.getClass().getName());
        return userService.find();
    }

    @ApiOperation(value = "feign restful测试")
    @GetMapping("/{name}")
    public String get(@PathVariable String name, @RequestParam int age) {
        return userService.get(name,age);
    }

    @ApiOperation(value = "feign测试")
    @GetMapping("/say/hello")
    public String sayHello(@RequestParam String name) {
        return userService.sayHello(name);
    }


    @ApiOperation(value = "feign不带参数测试")
    @GetMapping()
    public String getHello() {
        return userService.getHello();
    }
}

