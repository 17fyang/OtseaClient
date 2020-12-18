package com.stu.otseaclient.general;

import android.content.Context;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 20:22
 * @Description: 同步变量类，用来线程间的变量共享
 */
public class SyncPool {
    private static SyncPool instance = new SyncPool();

    public static SyncPool getInstance() {
        return instance;
    }

    // 用户handle中的activity切换，表示目标activity
    private volatile Class<? extends Context> targetClass;

    public Class<? extends Context> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<? extends Context> targetClass) {
        this.targetClass = targetClass;
    }
}

