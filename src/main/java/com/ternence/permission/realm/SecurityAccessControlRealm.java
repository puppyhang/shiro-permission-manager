package com.ternence.permission.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * create by 陶江航 at 2017/10/21 22:18
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 实现认证和授权的Realm类
 */
public class SecurityAccessControlRealm extends AuthorizingRealm {
    /**
     * 授权方法
     *
     * @param principals 用户的身份信息
     * @return 授权信息, 使得shiro能够识别权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证方法
     *
     * @param token 用于帮助认证的信息
     * @return 如果认证通过则返回用户的身份信息，否则返回null
     * @throws AuthenticationException 认证发生异常，直接抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
