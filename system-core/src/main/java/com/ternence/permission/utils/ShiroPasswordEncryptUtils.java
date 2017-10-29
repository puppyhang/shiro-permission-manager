package com.ternence.permission.utils;

import com.google.common.base.Preconditions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * create by 陶江航 at 2017/10/22 9:05
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 使用shiro的密码加密工具计算密码对应的hash值
 */
public class ShiroPasswordEncryptUtils {

    public static String encrypt(String password, String salt) {
        Preconditions.checkNotNull(password, "密码不能为null");
        Preconditions.checkNotNull(salt, "盐不能为null");
        SimpleHash hash = new SimpleHash("MD5",
                password.toCharArray(),
                ByteSource.Util.bytes(salt), 2);
        /*shiro的凭证匹配器用的是toString方法*/
        return hash.toString();
    }

}
