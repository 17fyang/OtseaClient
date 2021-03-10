package com.stu.otseaclient.temp;

import android.view.View;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.util.UiUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/7 22:23
 * @Description:
 */
public class NetTestListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        String url = ApiEnum.USER_LOGIN;

        RequestBody formBody = new FormBody.Builder()
                .add("mail", "17fyang@stu.edu.cn")
                .add("password", "123456")
                .build();

        HttpRequest.getInstance().asyncPostRest(url, formBody, (rest) -> {
            UiUtil.sendToast("连接成功：" + rest.getMsg());
        });

    }
}
