package com.ternence.permission.anon;

import java.lang.annotation.*;

/**
 * create by 陶江航 at 2017/10/22 12:41
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 修饰mybatis的mapper接口
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBatisMapper {
    //nothing
}
