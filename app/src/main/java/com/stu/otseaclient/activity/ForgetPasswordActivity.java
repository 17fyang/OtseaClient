package com.stu.otseaclient.activity;

import android.os.Bundle;
import com.stu.otseaclient.general.GeneralHandle;

public class ForgetPasswordActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_password);

        //初始化handle
        GeneralHandle.getInstance().setCurContext(this);

    }

}
