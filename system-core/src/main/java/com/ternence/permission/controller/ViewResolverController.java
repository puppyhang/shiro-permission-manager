package com.ternence.permission.controller;

import com.ternence.permission.base.controller.AbstractSystemController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by 陶江航 at 2017/10/25 22:20
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 视图解析的Controller，负责返回页面的Controller
 */
@Controller
public class ViewResolverController extends AbstractSystemController {
    @Override
    public Class getLoggerName() {

        return getClass();
    }


    @RequestMapping("/index")
    public String index() {

        return "home";
    }
}
