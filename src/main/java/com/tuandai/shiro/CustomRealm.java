package com.tuandai.shiro;

import com.tuandai.config.Auth2Token;
import com.tuandai.shiro.dao.PermissionDAO;
import com.tuandai.shiro.dao.RoleDAO;
import com.tuandai.shiro.dao.UserDAO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by 肖文 on 2018/6/12
 * 自定义Realm实现验证
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    public CustomRealm(UserDAO userDAO, RoleDAO roleDAO, PermissionDAO permissionDAO) {
        this.userDAO = userDAO;
        this.permissionDAO = permissionDAO;
        this.roleDAO = roleDAO;
    }


    /**
     * 必须重写 保证在登录校验时类型转化正常
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Auth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName= (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByName(userName);
        Set<String> permissions = getPermissionsByName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    //数据库获取权限信息
    private Set<String> getPermissionsByName(String userName) {
        return this.permissionDAO.getPermissionsByName(userName);
    }

    //数据库获取角色信息
    private Set<String> getRolesByName(String userName) {
        return this.roleDAO.getRolesByName(userName);
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //从主体传输过来的认证信息，获取用户名
        String username= (String) authenticationToken.getPrincipal();
//        System.out.println(username+"======================");
//        //通过用户名从数据库获取凭证
//        String password = getPasswordByUserName(username);
//        if (password == null) {
//            return null;
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("xiaowen"
                ,username,getName());
        return authenticationInfo;
    }

    //从数据库获取凭证
    private String getPasswordByUserName(String username) {
        System.out.println(this.userDAO+"==============");
        String password = this.userDAO.getPasswordByUserName(username);
        return password;
    }
}
