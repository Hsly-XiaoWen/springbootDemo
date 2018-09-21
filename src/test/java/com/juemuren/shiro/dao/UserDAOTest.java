package com.juemuren.shiro.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 肖文 on 2018/6/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void getPasswordByUserName() throws Exception {
        String password=this.userDAO.getPasswordByUserName("xiaowen");
        System.out.println(password+"========");
    }

}