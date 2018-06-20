package com.tuandai.shiro;

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

import java.util.Set;

/**
 * Created by 肖文 on 2018/6/12
 * 自定义Realm实现验证
 */
public class CustomRealm extends AuthorizingRealm {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private PermissionDAO permissionDAO;

    public CustomRealm(UserDAO userDAO, RoleDAO roleDAO, PermissionDAO permissionDAO) {
        this.userDAO = userDAO;
        this.permissionDAO = permissionDAO;
        this.roleDAO = roleDAO;
    }

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
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //从主体传输过来的认证信息，获取用户名
        String username= (String) authenticationToken.getPrincipal();
        System.out.println(username+"======================");
        //通过用户名从数据库获取凭证
        String password = getPasswordByUserName(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("xiaowen"
                ,password,"test");
        return authenticationInfo;
    }

    //从数据库获取凭证
    private String getPasswordByUserName(String username) {
        System.out.println(this.userDAO+"==============");
        String password = this.userDAO.getPasswordByUserName(username);
        return password;
    }
}
