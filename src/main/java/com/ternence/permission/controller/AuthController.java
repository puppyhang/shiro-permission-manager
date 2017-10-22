package com.ternence.permission.controller;

import com.ternence.permission.base.AbstractSystemController;
import com.ternence.permission.dto.LoginParamBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * create by 陶江航 at 2017/10/22 0:37
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 认证相关的接口
 */
@Controller
public class AuthController extends AbstractSystemController {

    @Override
    public String getLoggerName() {

        return getClass().getName();
    }

    /**
     * @param req 请求对象，shiro会把登录信息放在请求对象中发送过来
     * @return 根据逻辑返回不同的页面
     * <p>
     * 处理用户登陆的方法, 登陆逻辑是:
     * 使用{@link FormAuthenticationFilter}过虑器实现登陆,
     * 将用户没有认证时，请求loginurl进行认证，用户身份和用户密码提交数据到loginurl
     * FormAuthenticationFilter拦截住取出request中的username和password（两个参数名称是可以配置的）
     * FormAuthenticationFilter调用realm传入一个token（username和password）
     * realm认证时根据username查询用户信息，如果查询不到，realm返回null，FormAuthenticationFilter向request域中填充一个参数shiroLoginFailure（记录了异常信息）
     * 如果登陆成功则自动跳转到上一个页面，登录失败的话还是回到登录页面,页面吧信息给用户看，旧时代的jsp这种写法没问题，
     * 新时代的前后分离这就有问题了，页面不应该由后台返回，那么很多逻辑就需要前段自己掌控了
     * <p>
     * 这种方式适合web管理后台和页面不分开的登录逻辑
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest req, Model model) {
        String errorClassName = (String) req.getAttribute("shiroLoginFailure");
        getLogger().info("errorClassName:{}", errorClassName);
        String authenticationError = null;
        if (UnknownAccountException.class.getName().equals(errorClassName)) {
            authenticationError = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            authenticationError = "用户名/密码错误";
        } else if (AuthenticationException.class.getName().equals(errorClassName)) {
            authenticationError = "登陆失败";
        }
        model.addAttribute("authenticationError", authenticationError);
        //这个日志表明这个方法的工作原理
        getLogger().info(authenticationError == null ? "请求登录" : "登录失败,原因是" + authenticationError);
        return "login";
    }

    /**
     * 自己动手写的登录逻辑，方便理解shiro的拦截器的工作原理
     * <p>
     * 这种方式适合客户端的等咯，包括前后分离的前段登录
     *
     * @param loginParam 登录参数
     * @param result     参数绑定结果
     * @return 登录结果的数据
     */
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

}
