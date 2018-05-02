package com.tuandai.test;

import com.tuandai.controller.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by 肖文 on 2018/4/20
 */
public class HelloControllerTest {

    private MockMvc mvc;
    //初始化执行
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    /**
     * 模拟测试Controller判断是否正确，get请求
     * @throws Exception
     */
    @Test
    public void sayHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/hello?age=31").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void hello() throws Exception {

    }

    /*验证controller是否正常响应并打印返回结果正确，Post请求*/
    @Test
    public void name() throws Exception {
        String name = "xiaowen";
       /* mvc.perform((MockMvcRequestBuilders.post("/user/name/test")).param("name",name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();*/
        this.mvc.perform(MockMvcRequestBuilders.post("/user/name?age=11").param("name", name)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("name:11"));
    }

    @Test
    public void getUser() throws Exception {
    }

}