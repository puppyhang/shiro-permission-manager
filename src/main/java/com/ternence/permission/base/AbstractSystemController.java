package com.ternence.permission.base;

import com.google.common.base.Preconditions;
import com.ternence.permission.dto.ResultDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * create by 陶江航 at 2017/10/21 21:56
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 对controller的系统级别的抽象, 定义统一的异常处理，渲染不通类型的响应数据等等
 */
@ControllerAdvice
public abstract class AbstractSystemController implements RestfulController {
    //一个controller所有线程共享一个logger
    private Logger logger = null;

    public AbstractSystemController() {
        synchronized (this) {
            if (logger == null) {
                String loggerName = getLoggerName();
                Preconditions.checkNotNull(loggerName, "日志名称不能为null");
                logger = LoggerFactory.getLogger(loggerName);
            }
        }
    }

    /**
     * 设置日志打引器的名称
     *
     * @return 用户设置的名称
     */
    abstract public String getLoggerName();

    /**
     * @return 根据用户设置的loggerName返回一个日志打引器
     * @see #getLoggerName()
     */
    public Logger getLogger() {

        return logger;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Throwable.class})
    public Object exceptionHandler(Exception e) {
        getLogger().info("发生异常:", e);
        //返回异常信息就行了
        return renderingFailureResponseData(e.getMessage());
    }

    @Override
    public <T> ResultDataBean<T> renderingResponseData(String code, String message, T data) {
        ResultDataBean<T> result = new ResultDataBean<>();
        Preconditions.checkNotNull(code, "code 不能为null");
        Preconditions.checkNotNull(message, "message 不能为null");
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public <T> ResultDataBean<T> renderingSuccessResponseData(T data) {

        return renderingResponseData("200", "请求成功", data);
    }

    public <T> ResultDataBean<T> renderingSuccessResponseData(T data, String message) {

        return renderingResponseData("200", message, data);
    }

    public <T> ResultDataBean<T> renderingFailureResponseData(T data) {

        return renderingResponseData("400", "请求失败", data);
    }

    public <T> ResultDataBean<T> renderingFailureResponseData(T data, String message) {

        return renderingResponseData("400", message, data);
    }
}
