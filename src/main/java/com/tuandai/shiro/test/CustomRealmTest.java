package com.tuandai.shiro.test;

import com.tuandai.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by 肖文 on 2018/6/12
 */
public class CustomRealmTest {

    private CustomRealm customRealm = new CustomRealm();

    @Test
    public void testAuthentication() {
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
