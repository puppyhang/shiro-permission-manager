package com.ternence.permission.controller;

import com.ternence.permission.base.AbstractSystemController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by 陶江航 at 2017/10/21 21:13
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 用户和角色管理的controller
 */
@Controller
public class UserRoleManageController extends AbstractSystemController {

    @Override
    public String getLoggerName() {
        return getClass().toString();
    }

    @RequestMapping("/user/add")
    public Object requestAddUser() {
        getLogger().info("请求新增一个用户");
        return null;
    }
}
