package com.ternence.permission.service;

import com.ternence.permission.model.SysMenu;
import com.ternence.permission.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by 陶江航 at 2017/10/29 14:58
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 操作系统菜单的Service
 */
public interface SysMenuService {
    /**
     * 根据用户信息查询系统菜单,只查询第一级菜单
     *
     * @param sysUser 用户信息
     * @return 菜单列表
     */
    List<SysMenu> getSysMenuByUser(@Param("user") SysUser sysUser);

    /**
     * 根据某一个系统菜单查询对应的子菜单
     *
     * @param parentMenu 父菜单
     * @return 所有的子菜单
     */
    List<SysMenu> getChildrenSysMenuByParentMenu(@Param("menu") SysMenu parentMenu);
}
