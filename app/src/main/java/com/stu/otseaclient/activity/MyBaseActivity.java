package com.stu.otseaclient.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.stu.otseaclient.general.GeneralHandle;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 19:54
 * @Description:
 */
public class MyBaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        GeneralHandle.getInstance().setCurContext(this);
    }


}
