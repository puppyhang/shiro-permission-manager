package com.ternence.permission.mapper;

import com.ternence.permission.anon.MyBatisMapper;
import com.ternence.permission.model.SysUser;
import com.ternence.permission.model.SysUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@MyBatisMapper
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}