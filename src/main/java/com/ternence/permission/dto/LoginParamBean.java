package com.ternence.permission.dto;

/**
 * create by 陶江航 at 2017/10/22 11:34
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 登录参数对应的bean
 */
public class LoginParamBean {
    private String username;
    private String password;
    private Boolean rememberMe;

    public LoginParamBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginParamBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
