package com.ternence.permission.base;

import com.ternence.permission.log.Logable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by 陶江航 at 2017/10/29 15:17
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 实现一个可打印日志的组件
 */
public abstract class LogableComponent implements Logable {
    private Logger logger = LoggerFactory.getLogger(getLoggerName());

    /**
     * 留给用户自己设置想要的日志记录器的名称
     * 不能返回null
     *
     * @return 日志记录器的名称
     */
    abstract protected Class getLoggerName();

    /**
     * @return 根据用户设置的loggerName返回一个日志打引器
     * @see #getLoggerName()
     */
    @Override
    public Logger getLogger() {

        if (getLoggerName() == null) {

            throw new IllegalArgumentException("日志记录器名称不能为null");
        }

        return logger;
    }
}
