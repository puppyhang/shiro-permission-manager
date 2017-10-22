package com.ternence.permission.commmon.test;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * create by 陶江航 at 2017/10/22 13:38
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 仅仅使用Junit来写的一个测试生成代码工具
 */
public class HashGenerateUtils {
    private SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Test
    public void generateSaltAndPassword() {
        String salt = randomNumberGenerator.nextBytes().toHex();
        SimpleHash hash = new SimpleHash("MD5");
        hash.setIterations(2);
        hash.setSalt(ByteSource.Util.bytes(salt));
    }
}
