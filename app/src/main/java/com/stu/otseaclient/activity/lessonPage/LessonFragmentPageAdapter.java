package com.stu.otseaclient.activity.lessonPage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.stu.otseaclient.pojo.LessonInfo;
import com.video.player.lib.view.VideoPlayerTrackView;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:01
 * @Description:
 */
public class LessonFragmentPageAdapter extends FragmentPagerAdapter {
    public static final Fragment[] totalFragment = new Fragment[3];

    public LessonFragmentPageAdapter(VideoPlayerTrackView mVideoPlayer, LessonInfo lessonInfo, FragmentManager fm, int behavior) {
        super(fm, behavior);
        totalFragment[LessonDetailActivity.LESSON_INTRO] = new LessonIntroFragment(mVideoPlayer, lessonInfo);
        totalFragment[LessonDetailActivity.LESSON_DIR] = new LessonDirFragment(mVideoPlayer, lessonInfo);
        totalFragment[LessonDetailActivity.TALK] = new LessonTalkFragment(mVideoPlayer, lessonInfo);
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
