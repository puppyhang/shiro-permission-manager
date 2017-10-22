package com.ternence.permission.realm;

import com.ternence.permission.model.SysUser;
import com.ternence.permission.service.SysUserService;
import com.ternence.permission.utils.ShiroPasswordEncryptUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * create by 陶江航 at 2017/10/21 22:18
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 实现认证和授权的Realm类
 */
public class SecurityAccessControlRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //只支持UsernamePasswordToken这种类型的用户信息验证
        return token instanceof UsernamePasswordToken;
    }

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
     * @throws AuthenticationException 认证发生异常，直接抛出异常,抛出的异常会被以下方法拦截
     * @see org.apache.shiro.mgt.DefaultSecurityManager#login(Subject, AuthenticationToken)
     * @see HashedCredentialsMatcher#doCredentialsMatch(AuthenticationToken, AuthenticationInfo)
     * <p>
     * shiro的HashedCredentialsMatcher在比较密码的时候，
     * 它使用的加密方式是直接返回一个SimpleHash，相当于调用toString方法，
     * 而不是调用toHex方法，所以我们在生成hash的密码的时候也需要是使用toString方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SysUser sysUser = userService.findUserByAccountName(username);
        if (sysUser == null) {
            throw new UnknownAccountException("没有该用户存在");
        }
        ByteSource salt = ByteSource.Util.bytes(sysUser.getSalt());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser.getLoginName(),
                sysUser.getPwd(), salt, getName());
        logger.info("原始盐为:{}", ByteSource.Util.bytes("99ff91cbb4e928ef1696593c10d184fe"));
        logger.info("认证盐为:{}", salt);
        return authenticationInfo;
    }
}
