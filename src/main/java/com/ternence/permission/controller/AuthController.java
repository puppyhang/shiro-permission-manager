package com.ternence.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by 陶江航 at 2017/10/22 0:37
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 认证相关的接口
 */
@Controller
public class AuthController {

    @RequestMapping("/login")
    public String login(){

        return "login";
    }
}
