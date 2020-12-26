package com.stu.otseaclient.component.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.enumreation.TagEnum;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.util.MessageUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 15:45
 * @Description:
 */
public class NetImageView extends androidx.appcompat.widget.AppCompatImageView {

    public NetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NetImageView(Context context) {
        super(context);
        init();
    }

    public NetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化设置handle监听
     */
    private void init() {
        if (GeneralHandle.getInstance().hasKey(MessageKey.GET_NET_IMAGE)) return;
        GeneralHandle.getInstance().registerHandle(MessageKey.GET_NET_IMAGE, (curText, msg) -> {
            Log.e(TagEnum.TEST, "netImageView:" + this);
            Object[] objs = (Object[]) msg.obj;
            NetImageView imageView = (NetImageView) objs[0];
            Bitmap bitmap = (Bitmap) objs[1];
            imageView.setImageBitmap(bitmap);
        });
    }


    /**
     * 设置网络图片
     *
     * @param path
     */
    public void setImageURL(String path) {
        //在子线程中进行io获取图片流
        HttpRequest.getInstance().asyncGetStream(path, (stream) -> {
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            Message msg = new Message();
            msg.what = MessageKey.GET_NET_IMAGE;
            msg.obj = new Object[]{this, bitmap};
            MessageUtil.sendMessage(msg);
        });
    }
}
