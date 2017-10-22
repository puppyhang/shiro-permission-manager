package com.ternence.permission.service;

import com.ternence.permission.model.SysUser;

/**
 * create by 陶江航 at 2017/10/21 22:23
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 用户对应的Service
 */
public interface SysUserService {

    SysUser findUserByAccountName(String name);

}
