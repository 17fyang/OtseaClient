package com.stu.otseaclient.activity.mainPage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/16 21:23
 * @Description:
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    public static final Fragment[] totalFragment = new Fragment[4];


    public MyFragmentPageAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        totalFragment[MainActivity.MAIN_FRAGMENT] = new MainFragment();
        totalFragment[MainActivity.DISCOVERY_FRAGMENT] = new DiscoveryFragment();
        totalFragment[MainActivity.LESSON_FRAGMENT] = new LessonFragment();
        totalFragment[MainActivity.MINE_FRAGMENT] = new MineFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return totalFragment[position];
    }

    @Override
    public int getCount() {
        return totalFragment.length;
    }
}
