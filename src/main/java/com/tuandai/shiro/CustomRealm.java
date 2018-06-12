package com.tuandai.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by 肖文 on 2018/6/12
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //从主体传输过来的认证信息，获取用户名
        String username= (String) authenticationToken.getPrincipal();
        //通过用户名从数据库获取凭证
        String password = getPasswordByUserName(username);
        if (password == null) {
            return null;
        }
//        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo("")
        return null;
    }

    //从数据库获取凭证
    private String getPasswordByUserName(String username) {
        return "";
    }
}
