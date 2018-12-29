package com.marsfood.framework.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 无状态的token 它的实现类系统默认实现了一个UsernamePasswordToken
 * @author shenkai
 * @date 2018/12/29
 */
public class StatelessToken implements AuthenticationToken {
    private String username;
    private String passowrd;


    public StatelessToken(String username, String passowrd) {
        this.username = username;
        this.passowrd = passowrd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
