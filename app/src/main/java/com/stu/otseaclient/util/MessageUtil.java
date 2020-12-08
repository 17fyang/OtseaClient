package com.stu.otseaclient.util;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.general.SyncPool;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 19:58
 * @Description:供子线程调用，需要向主线程发送message
 */
public class MessageUtil {
    private static MessageUtil instance = new MessageUtil();

    public static MessageUtil getInstance() {
        return instance;
    }

    private MessageUtil() {
    }

    /**
     * 弹出toast
     *
     * @param info
     */
    public void sendToast(String info) {
        Bundle bundle = new Bundle();
        bundle.putString("info", info);
        this.sendMessage(MessageKey.TOAST, bundle);
    }

    public void switchActivity(Class<? extends Context> targetActivity) {
        SyncPool.getInstance().setTargetClass(targetActivity);
        this.sendMessage(MessageKey.SWITCH_ACTIVITY, null);
    }

    public void sendMessage(int what, Bundle bundle) {
        Message message = new Message();
        message.what = what;
        message.setData(bundle);
        GeneralHandle.getInstance().sendMessage(message);
    }

}
