package com.ternence.permission.listener;

/**
 * create by 陶江航 at 2017/10/21 21:06
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description logback的启动监听, 在这里初始化一些常用的配置信息
 * <p>
 * 注入 LOG_FILE_PATH 表示日志文件的路径
 */
public class LogbackStartupListener extends AbstractLogbackStartupListener {
    private boolean isStarted = false;

    @Override
    public void start() {
        if (isStarted()) return;
        String userHome = System.getProperty("user.home");

        context.putProperty("LOG_FILE_PATH", userHome);

        System.out.println("日志启动,初始化日志存放路径为:" + userHome);

        isStarted = true;
    }

    @Override
    public boolean isStarted() {

        return isStarted;
    }
}
