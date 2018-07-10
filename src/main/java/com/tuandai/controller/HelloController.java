package com.tuandai.controller;

import com.tuandai.config.spring.ApiVersion;
import com.tuandai.config.spring.Version;
import com.tuandai.dto.RequestDTO;
import com.tuandai.dto.Result;
import com.tuandai.entiy.Person;
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

    @ApiOperation("测试自定义requestMapping")
    @GetMapping("/version1")
    @ApiVersion(version = Version.V_1_1_0)
    public String sayHello() {
        return "你好，我是最低版本的返回字段";
    }

    @ApiOperation("测试自定义requestMapping")
    @GetMapping("/version1")
    @ApiVersion(version = Version.V_1_1_2)
    public String sayHello2() {
        return "恭喜你获取最新版本的返回值";
    }

    @ApiOperation("测试网关")
    @PostMapping("/login")
    public Result sayHello(@RequestBody RequestDTO<User> requestDTO) {
        User user = requestDTO.getData();
        user.setUserId(2418744);
        System.out.println(user.toString());
        return Result.success(user);
    }

    @ApiOperation("测试请求参数大小写问题")
    @PostMapping("/testJson")
    public Result testJson(@RequestBody RequestDTO<Person> requestDTO) {
        Person person = requestDTO.getData();
        System.out.println(person.toString()+"==================");
        return Result.success(person);
    }

    @ApiOperation("测试网关")
    @PostMapping("/text")
    public Result auth(@RequestBody RequestDTO<User> requestDTO) {
        System.out.println(requestDTO.getUserId()+"==============");
        User user = requestDTO.getData();
        System.out.println(user.toString());
        return Result.success(user);
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
