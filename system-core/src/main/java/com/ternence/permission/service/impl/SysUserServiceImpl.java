package com.ternence.permission.service.impl;

import com.google.common.base.Preconditions;
import com.ternence.permission.mapper.SysUserMapper;
import com.ternence.permission.model.SysUser;
import com.ternence.permission.model.SysUserExample;
import com.ternence.permission.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by 陶江航 at 2017/10/21 22:24
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 用户Service的实现
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserByAccountName(String name) {
        Preconditions.checkNotNull(name, "账户名称不能为null");
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andLoginNameEqualTo(name);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null) {
            return sysUsers.get(0);
        }
        return null;
    }
}
