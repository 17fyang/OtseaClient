package com.stu.otseaclient.util;

import android.content.Context;
import android.os.Bundle;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.SyncPool;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 16:08
 * @Description:
 */
public class UiUtil {

    /**
     * 弹出toast
     *
     * @param info
     */
    public static void sendToast(String info) {
        Bundle bundle = new Bundle();
        bundle.putString("info", info);
        MessageUtil.sendBundle(MessageKey.TOAST, bundle);
    }

    /**
     * 切换Activity
     *
     * @param targetActivity
     */
    public static void switchActivity(Class<? extends Context> targetActivity) {
        SyncPool.getInstance().setTargetClass(targetActivity);
        MessageUtil.sendBundle(MessageKey.SWITCH_ACTIVITY, null);
    }
}
