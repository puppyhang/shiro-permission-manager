package com.ternence.permission.controller;

import com.ternence.permission.base.AbstractSystemController;
import com.ternence.permission.dto.LoginParamBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.AccountNotFoundException;

/**
 * create by 陶江航 at 2017/10/22 0:37
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 认证相关的接口
 */
@Controller
public class AuthController extends AbstractSystemController {

    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @RequestMapping("/auth/login")
    @ResponseBody
    public Object requestLogin(@Validated LoginParamBean loginParam, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return renderingFailureResponseData(null, "登录失败");
        }
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            //没有被验证过的话就执行shiro的登录逻辑
            token.setRememberMe(loginParam.getRememberMe() == null ? false
                    : loginParam.getRememberMe());
            try {
                subject.login(token);
            } catch (Exception e) {
                e.printStackTrace();//打印栈信息
                if (e instanceof IncorrectCredentialsException) {
                    throw new IncorrectCredentialsException("密码错误");
                }
                if (e instanceof UnknownAccountException) {
                    throw new UnknownAccountException("未知的账户");
                }
                if (e instanceof AuthenticationException) {
                    throw new AuthenticationException("认证失败");
                }
                throw e;//向上一层抛出异常，由异常处理器解决
            }
        }
        //else 这个用户被shiro记住过了,并且验证通过，那么直接登录成功
        return renderingSuccessResponseData(null, "登录成功");
    }

    @Override
    public String getLoggerName() {
        return getClass().getName();
    }
}
