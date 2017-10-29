package com.ternence.permission.service.impl;

import com.ternence.permission.base.LogableComponent;
import com.ternence.permission.model.SysMenu;
import com.ternence.permission.model.SysUser;
import com.ternence.permission.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by 陶江航 at 2017/10/29 15:04
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 系统菜单服务的实现类
 */
@Service
public class SysMenuServiceImpl extends LogableComponent implements SysMenuService {

    @Override
    protected Class getLoggerName() {

        return getClass();
    }

    @Override
    public List<SysMenu> getSysMenuByUser(SysUser sysUser) {

        return null;
    }

    @Override
    public List<SysMenu> getChildrenSysMenuByParentMenu(SysMenu parentMenu) {

        return null;
    }
}
