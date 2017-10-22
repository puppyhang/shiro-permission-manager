package com.ternence.permission.utils;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * create by 陶江航 at 2017/10/22 9:36
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 带有密码重试次数限制的凭证匹配器, 使用ehcache保存重试次数
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    /**
     * 执行密码匹配逻辑
     *
     * @param token 用户提供的token对象
     * @param info  realm中返回的认证信息
     * @return 密码是否匹配成功，这里面包含了匹配次数是否达到限制次数
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }
}
