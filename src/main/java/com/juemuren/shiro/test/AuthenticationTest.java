package com.juemuren.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 肖文 on 2018/6/9
 */
public class AuthenticationTest {


    private SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();

    @Before
    public void init() {
        simpleAccountRealm.addAccount("xiaowen","123456");
        simpleAccountRealm.addAccount("juemuren","123456","admin","user");
    }

    /**
     * 测试身份验证
     */
    @Test
    public void testAuthentication() {
        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);

        //主体提交认证请求
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken("juemuren","123456");
        //身份校验
        try {
            subject.login(token);
            System.out.println("用户是否已经验证："+subject.isAuthenticated());
        } catch (Exception e) {
            System.out.println("验证失败"+e.getMessage());
        }
        subject.checkRoles("user");
    }

}
