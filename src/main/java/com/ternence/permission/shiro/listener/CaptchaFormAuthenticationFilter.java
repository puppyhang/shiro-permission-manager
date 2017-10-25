package com.ternence.permission.shiro.listener;

import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.ternence.permission.ex.CapatchaErrorException;
import com.ternence.permission.shiro.captcha.CaptchaEngine;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * create by 陶江航 at 2017/10/22 21:25
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 带有验证码校验功能的表单登录过滤器
 * <p>
 * 验证码使用JCaptcha框架生成
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private GenericManageableCaptchaService captchaService;

    /**
     * 在访问资源框架内抛出 {@link UnauthenticatedException}的时候会调用这个方法
     *
     * @param request  请求对象
     * @param response 响应对象
     * @return 登录成功{@code true}  登录失败{@code false}
     * @throws Exception 可能抛出异常
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 在执行具体的登陆逻辑之前先校验验证码
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        // 取出请求中的验证码与生成的验证码比较
        String paramCaptcha = req.getParameter("captcha");
        if (paramCaptcha != null) {
            //如果验证码校验通过
            if (captchaService.validateResponseForID(session.getId(), paramCaptcha)) {
                // 比较通过，继续执行登陆逻辑
                return super.executeLogin(request, response);
            } else {
                // 验证码错误
                request.setAttribute("shiroLoginFailure",
                        CapatchaErrorException.class.getCanonicalName());
            }
        } else {
            // 验证码为null
            request.setAttribute("shiroLoginFailure",
                    CapatchaErrorException.class.getCanonicalName());
        }
        // 返回true表明已经处理登陆逻辑
        return true;
    }
}
