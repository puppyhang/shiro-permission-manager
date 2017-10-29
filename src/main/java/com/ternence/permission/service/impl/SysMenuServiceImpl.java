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

    /**
     * 1:根据用户id查询到用户的角色信息
     * 2:根据角色查询到对应的权限列表
     * 3:根据权限查询到对应的菜单并去重就得到了用户的菜单
     *
     * @param sysUser 用户信息
     * @return 菜单集合
     */
    @Override
    public List<SysMenu> getSysMenuByUser(SysUser sysUser) {

        return null;
    }

    @Override
    public List<SysMenu> getChildrenSysMenuByParentMenu(SysMenu parentMenu) {

        return null;
    }
}
