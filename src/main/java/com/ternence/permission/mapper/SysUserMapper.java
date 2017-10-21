package com.ternence.permission.mapper;

import com.ternence.permission.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}