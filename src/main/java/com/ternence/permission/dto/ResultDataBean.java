package com.ternence.permission.dto;

import java.io.Serializable;

/**
 * create by 陶江航 at 2017/10/21 21:22
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 返回给C端的数据对应的Java Bean封装类型
 */
public class ResultDataBean<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public ResultDataBean() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDataBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
