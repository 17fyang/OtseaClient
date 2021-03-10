package com.stu.otseaclient.activity.lessonPage;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewpager.widget.ViewPager;
import com.stu.com.R;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:09
 * @Description:
 */
public class LessonPageListener implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private final ViewPager viewPager;
    private final RadioButton[] radioButtons;

    public LessonPageListener(ViewPager viewPager, RadioButton[] radioButtons) {
        this.viewPager = viewPager;
        this.radioButtons = radioButtons;
    }

    /**
     * radioButton的点击监听
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.lesson_detail_intro_button:
                viewPager.setCurrentItem(LessonDetailActivity.LESSON_INTRO);
                break;
            case R.id.lesson_detail_dir_button:
                viewPager.setCurrentItem(LessonDetailActivity.LESSON_DIR);
                break;
            case R.id.lesson_detail_talk_button:
                viewPager.setCurrentItem(LessonDetailActivity.TALK);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //viewpage移动监听，state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2)
            radioButtons[viewPager.getCurrentItem()].setChecked(true);
    }


}
