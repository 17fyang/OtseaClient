package com.stu.otseaclient.general;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.enumreation.TagEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 18:50
 * @Description:
 */
public class GeneralHandle extends Handler {
    private static GeneralHandle instance;
    private Context curContext;
    private Map<Integer, IHandlerConsumer> consumerMap = new HashMap<>();

    public static GeneralHandle getInstance() {
        if (instance == null) instance = new GeneralHandle(Looper.myLooper());
        return instance;
    }

    private GeneralHandle(Looper looper) {
        super(looper);
        registerConsumer();
    }

    /**
     * 注册全局的consumerHandle，也可以在其他地方注册
     */
    private void registerConsumer() {
        //注册toast
        this.registerHandle(MessageKey.TOAST, (context, msg) -> Toast.makeText(this.curContext, msg.getData().getString("info"), Toast.LENGTH_SHORT).show());

        //注册切换activity
        this.registerHandle(MessageKey.SWITCH_ACTIVITY, (context, msg) -> {
            Class<? extends Context> targetContext = SyncPool.getInstance().getTargetClass();
            if (targetContext != null)
                this.curContext.startActivity(new Intent(this.curContext, targetContext));
        });
    }

    /**
     * 处理handle接收到的msg，先查询已注册的consumer，然后交给consumer处理
     *
     * @param msg
     */
    @Override
    public void handleMessage(Message msg) {
        IHandlerConsumer consumer = consumerMap.get(msg.what);
        if (consumer != null) {
            consumer.handle(curContext, msg);
        } else {
            Log.e(TagEnum.SYSTEM, "consumer has no register by key:" + msg.what);
        }
    }

    /**
     * 注册一个Consumer和对应的key
     *
     * @param what
     * @param iHandlerConsumer
     */
    public void registerHandle(int what, IHandlerConsumer iHandlerConsumer) {
        if (!consumerMap.containsKey(what))
            consumerMap.put(what, iHandlerConsumer);
    }

    /**
     * 设置当前的activity，仅activity切换时调用
     *
     * @param context
     */
    public void setCurContext(Context context) {
        this.curContext = context;
    }
}
