package com.tuandai.shiro.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by 肖文 on 2018/6/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionDAOTest {

    @Autowired
    private PermissionDAO permissionDAO;

    @Test
    public void getPermissionsByName() throws Exception {
        Set<String> names = permissionDAO.getPermissionsByName("admin");
        names.forEach(System.out::println);
    }

}