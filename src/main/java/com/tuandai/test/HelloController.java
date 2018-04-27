package com.tuandai.test;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 肖文
 * @Description ${DESCRIPTION}
 * @date 2018/3/30 11:22
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    @RequestMapping("/{name}")
    public String sayHello(@PathVariable("name") String name, @RequestParam("age") int age) {
        return name + ":" + age;
    }

    @RequestMapping("/name/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        return cookies.toString();
    }
    @RequestMapping(value = "/name/test",method = RequestMethod.POST)
    public String name(@RequestParam("name") String name) {
        return "热加载测试+sadsad:::"+name;
    }

    @RequestMapping("/names/getUser")
    public User getUser() {
        return new User("xiaowen", "123213");
    }
}
