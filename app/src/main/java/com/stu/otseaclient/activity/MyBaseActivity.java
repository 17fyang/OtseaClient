package com.stu.otseaclient.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.stu.otseaclient.general.GeneralHandle;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 19:54
 * @Description:
 */
public class MyBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化全局handle
        GeneralHandle.getInstance().setCurContext(this);
    }
}
