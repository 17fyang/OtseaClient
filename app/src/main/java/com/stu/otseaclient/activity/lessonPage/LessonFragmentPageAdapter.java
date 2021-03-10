package com.stu.otseaclient.activity.lessonPage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:01
 * @Description:
 */
public class LessonFragmentPageAdapter extends FragmentPagerAdapter {
    public static final Fragment[] totalFragment = new Fragment[3];

    public LessonFragmentPageAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        totalFragment[LessonDetailActivity.LESSON_INTRO] = new LessonIntroFragment();
        totalFragment[LessonDetailActivity.LESSON_DIR] = new LessonDirFragment();
        totalFragment[LessonDetailActivity.TALK] = new LessonTalkFragment();
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
