package com.ternence.permission.log;

import org.slf4j.Logger;

/**
 * create by 陶江航 at 2017/10/29 15:07
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 这个接口被那些需要具备日志记录能力的类实现
 */
public interface Logable {
    /**
     * 从这个方法可以返回一个日志记录器
     *
     * @return Logger
     */
    Logger getLogger();

}
