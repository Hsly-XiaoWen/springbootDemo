package com.juemuren.restTemplate;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 肖文 on 2018/5/3
 */
@RestController
@RequestMapping("/rest")
public class RestTemplateDemo {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateDemo.class);
    @Autowired
    private RestTemplate restTemplate;

    /**
     * getForEntity,getForObject不带参请求
     * @return
     */
    @RequestMapping("/say")
    public String say() {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://10.100.98.50:10001/", String.class);
        //getForObject返回消息体的内容，头信息、返回码等信息没有
//        String result = restTemplate.getForObject("http://10.100.98.50:10001/", String.class);
        HttpStatus status = responseEntity.getStatusCode();
        int code = responseEntity.getStatusCodeValue();
        HttpHeaders header = responseEntity.getHeaders();
        logger.info("返回的数据是{},status{},code{},header{}", JSON.toJSON(responseEntity),
                status,code,header);
        return JSON.toJSONString(responseEntity.getBody());
    }

    /**
     * 使用getForEntity带参请求
     * @return
     */
    @RequestMapping("/says")
    public String sayHello(){
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://10.100.98.50:10001/say/hello?name={1}"
                ,String.class,"xiaowen");
        String result =this.restTemplate.getForObject("http://10.100.98.50:10001/say/hello?name={1}"
                ,String.class,"xiaowen");
        logger.info("返回数据是{}，result{}", JSON.toJSONString(responseEntity), result);
        return responseEntity.getBody();
    }

    /**
     * post请求，带多个参数
     * @return
     */
    @RequestMapping("/user")
    public String getUser() {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("name", "xiaowen");
        requestEntity.add("age", String.valueOf(21));
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://10.100.98.50:10001/users/user"
                ,requestEntity,String.class);
        return responseEntity.getBody();
    }

    /**
     * exchange 可以支持Get,Post方式，使用广泛
     */
    @RequestMapping("/test")
    public String testExchange(){
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("name", "xiaowen");
        requestEntity.add("age", "1");
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://10.100.98.50:10001/users/user"
                , HttpMethod.POST,null,String.class,requestEntity);
        logger.info("请求结果是{}",JSON.toJSONString(responseEntity));
        MultiValueMap<String, String> r = new LinkedMultiValueMap<>();
        ResponseEntity<String> result = this.restTemplate.exchange("http://10.100.98.50:10001/say/hello?name={1}"
                ,HttpMethod.GET,RestTemplateUtils.httpHeader(r),String.class,"xiaowen");
        logger.info("请求结果是{}",JSON.toJSONString(result));
        return "2222222222";
    }
}
