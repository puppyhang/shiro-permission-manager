package com.ternence.permission.base;

import com.ternence.permission.dto.ResultDataBean;

/**
 * create by 陶江航 at 2017/10/25 22:14
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 *
 * @description 只需要返回数据而不需要返回页面的Controller
 */
public interface RestfulController extends Controller{

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
