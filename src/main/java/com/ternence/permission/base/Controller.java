package com.ternence.permission.base;

import com.ternence.permission.dto.ResultDataBean;
import org.slf4j.Logger;

/**
 * create by 陶江航 at 2017/10/21 21:16
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 对所有controller的抽象
 */
public interface Controller {

    /**
     * @return 根据用户设置的loggerName返回一个日志打引器
     * @see #getLoggerName()
     */
    Logger getLogger();

    /**
     * 设置日志打引器的名称
     *
     * @return 用户设置的名称
     */
    String getLoggerName();

    /**
     * 渲染返回到C端的响应数据
     *
     * @param code    请求状态码
     * @param message 请求状态对应的描述信息
     * @param data    需要返回的真实数据
     * @param <T>     返回的真实数据的数据类型
     * @return 渲染返回到C端的响应数据
     */
    <T> ResultDataBean<T> renderingResponseData(String code, String message, T data);
}
