package com.juemuren.shiro.test;

import com.juemuren.shiro.CustomRealm;
import com.juemuren.shiro.dao.PermissionDAO;
import com.juemuren.shiro.dao.RoleDAO;
import com.juemuren.shiro.dao.UserDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 肖文 on 2018/6/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomRealmTest {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    @Test
    public void testAuthentication() {
        CustomRealm customRealm = new CustomRealm(userDAO,roleDAO,permissionDAO);
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("xiaowen", "123456");
        subject.login(token);
        System.out.println("用户是否已经验证：" + subject.isAuthenticated());
        System.out.println("用户是否已经验证：" + subject.hasRole("admin"));
        System.out.println("用户是否已经验证：" + subject.hasRole("user"));
        System.out.println("用户是否已经验证：" + subject.isPermitted("delete"));
    }
}
