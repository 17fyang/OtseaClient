package com.stu.otseaclient.component;

import android.app.Activity;
import androidx.fragment.app.Fragment;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/21 19:18
 * @Description:
 */
public class BaseFragment extends Fragment {
    protected Activity activity;

    public BaseFragment(Activity activity) {
        this.activity = activity;
    }
}
