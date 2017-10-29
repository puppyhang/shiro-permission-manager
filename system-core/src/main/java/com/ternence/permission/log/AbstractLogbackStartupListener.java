package com.ternence.permission.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

/**
 * create by 陶江航 at 2017/10/21 20:47
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description logback 启动监听的基类
 */
public abstract class AbstractLogbackStartupListener extends ContextAwareBase implements LifeCycle, LoggerContextListener {

    @Override
    public void stop() {
        System.out.println("stop() logback停止");
    }

    @Override
    public boolean isResetResistant() {
        System.out.println("logback isResetResistant return value is true");
        return true;
    }

    @Override
    public void onStart(LoggerContext context) {
        System.out.println("logback启动了");
    }

    @Override
    public void onReset(LoggerContext context) {
        System.out.println("logback重置了");
    }

    @Override
    public void onStop(LoggerContext context) {
        System.out.println("onStop() logback停止");
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
        System.out.println("onLevelChange() logback日志水平变化了:" + level.toString());
    }

}
