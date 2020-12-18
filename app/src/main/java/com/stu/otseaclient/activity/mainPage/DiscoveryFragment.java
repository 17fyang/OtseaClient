package com.stu.otseaclient.activity.mainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.stu.com.R;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/16 21:32
 * @Description:
 */
public class DiscoveryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        return view;
    }
}
