package com.ternence.permission.controller;

import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.ternence.permission.base.controller.AbstractSystemController;
import com.ternence.permission.dto.LoginParamBean;
import com.ternence.permission.ex.CaptchaErrorException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * create by 陶江航 at 2017/10/22 0:37
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 认证相关的接口
 */
@Controller
public class AuthController extends AbstractSystemController {
    @Autowired
    private GenericManageableCaptchaService captchaService;

    @Override
    public Class getLoggerName() {

        return getClass();
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
     * <p>
     * 还有登录失败返回的信息太少，需要自定义FormAuthenticationFilter实现获取更多的信息
     * <p>
     * 还有这个接口对扩展不友好，每次新增一个异常就要来这个做一个判断
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest req, Model model) {
        //判断是否登录,这里获取Subject永远不会为null的，如果为null内部会新建一个Subject绑定到当前线程
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            getLogger().info("这个人{}已经登录过了,直接跳转到主页面", subject);
            //如果他已经登录了就直接重定向到主页
            return "redirect:/";
        }
        String errorClassName = (String) req.getAttribute("shiroLoginFailure");
        getLogger().info("errorClassName:{}", errorClassName);
        String authenticationError = null;
        if (UnknownAccountException.class.getName().equals(errorClassName)) {
            authenticationError = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            authenticationError = "用户名/密码错误";
        } else if (AuthenticationException.class.getName().equals(errorClassName)) {
            authenticationError = "登陆失败";
        } else if (ExcessiveAttemptsException.class.getName().equals(errorClassName)) {
            authenticationError = "密码错误次数已达到五次,请明日再试";
        } else if (CaptchaErrorException.class.getName().equals(errorClassName)) {
            authenticationError = "验证码错误";
        }
        model.addAttribute("authenticationError", authenticationError);
        //这个日志表明这个方法的工作原理
        getLogger().info(authenticationError == null ? "请求登录" : "登录失败,原因是" + authenticationError);
        //返回登录页面
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


    /**
     * 生成验证码图片的接口
     *
     * @param httpServletRequest  请求对象
     * @param httpServletResponse 响应对象
     */
    @RequestMapping(value = "/auth/captcha")
    public void getCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        byte[] captchaChallengeAsJpeg;
        // 输出jpg的字节流
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String captchaId = httpServletRequest.getSession().getId();
            BufferedImage challenge = (BufferedImage) captchaService.getChallengeForID(captchaId,
                    httpServletRequest.getLocale());
            // a jpeg encoder
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            // flush it in the response
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (Exception e) {
            try {
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
