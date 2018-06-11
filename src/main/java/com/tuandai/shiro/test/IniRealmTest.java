package com.tuandai.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by 肖文 on 2018/6/9
 */
public class IniRealmTest {
    @Test
    public void testIniRealm() {
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        //构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(securityManager);

        //主体提交认证请求
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken("XiaoWen","123456");
        //身份校验
        try {
            subject.login(token);
            System.out.println("用户是否已经验证："+subject.isAuthenticated());
            subject.checkPermissions("user:delete");
        } catch (Exception e) {
            System.out.println("验证失败"+e.getMessage());
        }
    }
}
