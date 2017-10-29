package com.ternence.permission.shiro.matcher;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by 陶江航 at 2017/10/22 9:36
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 带有密码重试次数限制的凭证匹配器, 使用ehcache保存重试次数
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    //密码重试次数的缓存(其实如果把重试次数放在缓存中的话，没办法运用持久化的操作，
    // 比如没办法解除冻结状态，所以还是需要持久化的)
    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
        //注意 必须要使用这种方式获取缓存管理器
        @SuppressWarnings("ConstantConditions")
        CacheManager cacheManager = CacheManager.newInstance(
                CacheManager.class.getClassLoader().
                        getResource("ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    /**
     * 执行密码匹配逻辑
     *
     * @param token 用户提供的token对象
     * @param info  realm中返回的认证信息
     * @return 密码是否匹配成功，这里面包含了匹配次数是否达到限制次数
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
            String username = upToken.getUsername();
            Element element = passwordRetryCache.get(username);
            if (element == null) {//如果缓存中没有这个key相关的缓存，那么初始化一个元素值为0
                element = new Element(username, new AtomicInteger(0));
                passwordRetryCache.put(element);
            }
            AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
            if (retryCount.incrementAndGet() > 5) {
                throw new ExcessiveAttemptsException("今日密码错误次数已达到五次,请二十四小时后重试");
            }

            //执行密码验证
            boolean isCredentialsMatch = super.doCredentialsMatch(token, info);

            if (isCredentialsMatch) {
                //如果成功一次就移除这个key相关的缓存
                passwordRetryCache.remove(username);
            }

            return isCredentialsMatch;
        } else {
            throw new IllegalArgumentException("没有在用户信息中发现用户名,只支持UsernamePasswordToken！");
        }
    }


}
