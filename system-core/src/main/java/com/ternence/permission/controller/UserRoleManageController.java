package com.ternence.permission.controller;

import com.ternence.permission.base.controller.AbstractSystemController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    public Class getLoggerName() {

        return getClass();
    }

    /**
     * 请求新增一个用户的接口
     *
     * @return 是否新增成功
     */
    @RequiresPermissions("system:user:add")
    @RequestMapping("/user/add")
    public Object requestAddUser() {
        getLogger().info("请求新增一个用户");
        return null;
    }
}
