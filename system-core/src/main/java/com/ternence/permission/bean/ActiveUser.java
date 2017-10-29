package com.ternence.permission.bean;

import com.ternence.permission.model.SysMenu;
import com.ternence.permission.model.SysUser;

import java.util.List;

/**
 * create by 陶江航 at 2017/10/29 15:38
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 保存用户身份信息的Java bean
 */
public class ActiveUser {
    //用户名（主要身份表示）
    private String primaryPrincipal;
    //菜单
    private List<SysMenu> sysMenus;
    //数据库中的用户信息
    private SysUser sysUser;

    public ActiveUser() {
    }

    public String getPrimaryPrincipal() {
        return primaryPrincipal;
    }

    public void setPrimaryPrincipal(String primaryPrincipal) {
        this.primaryPrincipal = primaryPrincipal;
    }

    public List<SysMenu> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<SysMenu> sysMenus) {
        this.sysMenus = sysMenus;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "primaryPrincipal='" + primaryPrincipal + '\'' +
                ", sysMenus=" + sysMenus +
                ", sysUser=" + sysUser +
                '}';
    }
}
