package com.juemuren.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by 肖文 on 2018/6/19
 */
@FeignClient(name="user-service",fallback = UserServiceFallBack.class)
public interface UserService {

    //远程未实现调不通
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<Map> find();

    @RequestMapping(method = RequestMethod.GET, value = "/user/{name}")
    String get(@PathVariable("name") String name, @RequestParam("age") int age);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    String getHello();

    @RequestMapping(method = RequestMethod.GET, value = "/say/hello")
    String sayHello(@RequestParam(name = "name") String name);
}
