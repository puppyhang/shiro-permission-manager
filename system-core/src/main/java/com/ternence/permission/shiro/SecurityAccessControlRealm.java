package com.ternence.permission.shiro;

import com.ternence.permission.base.LogableAuthorizingRealm;
import com.ternence.permission.bean.ActiveUser;
import com.ternence.permission.model.SysMenu;
import com.ternence.permission.model.SysUser;
import com.ternence.permission.service.SysMenuService;
import com.ternence.permission.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.List;

/**
 * create by 陶江航 at 2017/10/21 22:18
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 实现认证和授权的Realm类
 */
public class SecurityAccessControlRealm extends LogableAuthorizingRealm {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    protected Class getLoggerName() {

        return getClass();
    }

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
        //获取用户的权限信息的方法,将用户的权限信息从这里返回,shiro才能判断是否这个用户具有权限
        return null;
    }

    /**
     * 认证方法
     * <p>
     * shiro的HashedCredentialsMatcher在比较密码的时候，
     * 它使用的加密方式是直接返回一个SimpleHash，相当于调用toString方法，
     * 而不是调用toHex方法，所以我们在生成hash的密码的时候也需要是使用toString方法
     * <p>
     * 为了个更加的可复用，查询用户菜单的功能需要做成一个单独的接口，方便整合的时候查询菜单
     *
     * @param token 用于帮助认证的信息
     * @return 如果认证通过则返回用户的身份信息，否则返回null
     * @throws AuthenticationException 认证发生异常，直接抛出异常,抛出的异常会被以下方法拦截
     * @see org.apache.shiro.mgt.DefaultSecurityManager#login(Subject, AuthenticationToken)
     * @see HashedCredentialsMatcher#doCredentialsMatch(AuthenticationToken, AuthenticationInfo)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SysUser sysUser = userService.findUserByAccountName(username);
        //这样跟容易找到问题的所有,而不是直接返回null
        if (sysUser == null) throw new UnknownAccountException("没有该用户存在");
        //查询菜单信息
        List<SysMenu> menus = sysMenuService.getSysMenuByUser(sysUser);
        ByteSource salt = ByteSource.Util.bytes(sysUser.getSalt());
        ActiveUser activeUser = new ActiveUser();
        activeUser.setSysUser(sysUser);
        activeUser.setSysMenus(menus);
        getLogger().info("用户信息为:{}", activeUser);
        return new SimpleAuthenticationInfo(activeUser,
                sysUser.getPwd(), salt, getName());
    }

}
