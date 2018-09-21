package com.juemuren.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by 肖文 on 2018/9/11
 */
public class Auth2Token implements AuthenticationToken {
    private String token;

    public Auth2Token(String token){
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
