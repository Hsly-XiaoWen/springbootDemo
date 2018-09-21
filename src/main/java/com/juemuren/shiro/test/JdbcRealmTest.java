package com.juemuren.shiro.test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 肖文 on 2018/6/12
 */
public class JdbcRealmTest {

    private JdbcRealm jdbcRealm = new JdbcRealm();

    @Before
    public void init() {
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis");
        dataSource.setPassword("28b21c1cfd");
        dataSource.setUser("root");
        dataSource.setServerName("localhost");
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setAuthenticationQuery("select password from user where username=?");
        jdbcRealm.setUserRolesQuery("select name from user_role where userId=(select id from user where username=?)");
        jdbcRealm.setPermissionsQuery("select name from role_permission where roleId in (select id from user_role " +
                "where name=?)");
    }

    @Test
    public void testJdbc() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("xiaowen","123456");
            subject.login(token);
            System.out.println("用户校验通过==============");
        }

        System.out.println("当前用户是："+subject.getPrincipal());

        if (subject.hasRole("admin")) {
            System.out.println("当前用户拥有admin角色");
        }
        if (subject.hasRole("user")) {
            System.out.println("当前用户拥有user角色");
        }
        System.out.println(subject.isPermitted("select"));
        if (subject.isPermitted("select")) {
            System.out.println("当前用户拥有select权限");
        }

        if (subject.isPermitted("delete")) {
            System.out.println("当前用户拥有delete权限");
        }

        if (subject.isPermitted("insert")) {
            System.out.println("当前用户拥有insert权限");
        }

    }
}
