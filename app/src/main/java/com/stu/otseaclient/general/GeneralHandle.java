package com.stu.otseaclient.general;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.stu.otseaclient.enumreation.MessageKey;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 18:50
 * @Description:
 */
public class GeneralHandle extends Handler {
    private static GeneralHandle instance;
    private Context curContext;

    public static GeneralHandle getInstance() {
        if (instance == null) instance = new GeneralHandle(Looper.myLooper());
        return instance;
    }

    private GeneralHandle(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case MessageKey.TOAST: {
                Toast.makeText(this.curContext, msg.getData().getString("info"), Toast.LENGTH_SHORT).show();
                break;
            }
            case MessageKey.SWITCH_ACTIVITY: {
                Class<? extends Context> targetContext = SyncPool.getInstance().getTargetClass();
                if (targetContext != null)
                    this.curContext.startActivity(new Intent(this.curContext, targetContext));
            }
        }
    }

    public void setCurContext(Context context) {
        this.curContext = context;
    }

}
