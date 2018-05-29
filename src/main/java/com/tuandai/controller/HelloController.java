package com.tuandai.controller;

import com.tuandai.test.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 肖文
 *
 * @date 2018/3/30 11:22
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    @ApiOperation("测试rest风格带参数")
    @GetMapping("/{name}")
    public String sayHello(@PathVariable("name") String name, @RequestParam("age") int age) {
        return name + ":" + age;
    }

    @ApiOperation("我就是试试cookie")
    @GetMapping("/name/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        return cookies.toString();
    }

    @ApiOperation("这下是post请求测试了")
    @PostMapping(value = "/name/test")
    public String name(@RequestParam("name") String name) {
        return "热加载测试+sadsad:::"+name;
    }

    @ApiOperation("请求参数是对象的测试")
    @GetMapping("/names/getUser")
    public User getUser() {
        int i=5/0;
        return new User("xiaowen", "123213");
    }
}
