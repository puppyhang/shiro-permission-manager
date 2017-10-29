package com.ternence.permission.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * create by 陶江航 at 2017/10/22 13:41
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 对shiro的密码加密器的测试
 */
public class ShiroPasswordEncryptUtilsTest {
    private SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void encrypt() throws Exception {
        //String saltSource = randomNumberGenerator.nextBytes().toHex();
        String saltSource = "99ff91cbb4e928ef1696593c10d184fe";
//        ByteSource salt = ByteSource.Util.bytes(saltSource);
//        logger.info("盐值为:{}", salt);
        String encryptedPwd = ShiroPasswordEncryptUtils.encrypt("a123456",
                saltSource);
        logger.info("加密之后的密码为:{}", encryptedPwd);
    }

}