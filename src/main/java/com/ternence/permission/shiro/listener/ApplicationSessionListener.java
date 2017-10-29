package com.ternence.permission.shiro.listener;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by 陶江航 at 2017/10/29 13:10
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 对shiro session的生命周期事件监听
 */
public class ApplicationSessionListener implements SessionListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStart(Session session) {

        logger.info("新建了一个session:{}", session);
    }

    @Override
    public void onStop(Session session) {

        logger.info("session{}停止了,可能是登出，也可能是程序调用了Session#stop方法", session);
    }

    @Override
    public void onExpiration(Session session) {
        //调用登出方法重新登录
        SecurityUtils.getSubject().logout();
        logger.info("session:{}过期了,需要重新登录", session);
    }
}
