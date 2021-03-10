package com.stu.otseaclient.activity.lessonPage;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.stu.com.R;
import com.stu.otseaclient.activity.MyBaseActivity;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.video.player.lib.manager.VideoPlayerManager;
import com.video.player.lib.manager.VideoWindowManager;
import com.video.player.lib.view.VideoPlayerTrackView;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/10 14:31
 * @Description:
 */
public class LessonDetailActivity extends MyBaseActivity {
    public static final int LESSON_INTRO = 0;
    public static final int LESSON_DIR = 1;
    public static final int TALK = 2;
    private VideoPlayerTrackView mVideoPlayer;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        PagerAdapter pagerAdapter = new LessonFragmentPageAdapter(getSupportFragmentManager(), 0);
        viewPager = findViewById(R.id.lesson_detail_show_view);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(LESSON_DIR);

        //设置radioGroup点击监听和viewPager监听
        RadioButton[] radioButtons = new RadioButton[3];
        LessonPageListener lessonPageListener = new LessonPageListener(viewPager, radioButtons);
        viewPager.addOnPageChangeListener(lessonPageListener);
        RadioGroup radioGroup = findViewById(R.id.lesson_tab_bar);
        radioGroup.setOnCheckedChangeListener(lessonPageListener);

        //设置radioGroup的几个button
        radioButtons[LESSON_INTRO] = findViewById(R.id.lesson_detail_intro_button);
        radioButtons[LESSON_DIR] = findViewById(R.id.lesson_detail_dir_button);
        radioButtons[TALK] = findViewById(R.id.lesson_detail_talk_button);

        //默认的页面是课程介绍界面
        radioButtons[LESSON_DIR].setChecked(true);

        //准备播放视频
        mVideoPlayer = findViewById(R.id.lesson_video_track);
        String path = ApiEnum.file("/video/test.mp4");
        mVideoPlayer.startPlayVideo(path, "这是一个测试标题");

    }

    @Override
    protected void onResume() {
        super.onResume();
        VideoPlayerManager.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        VideoPlayerManager.getInstance().onPause();
    }

    @Override
    public void onBackPressed() {
        //尝试弹射返回
        if (VideoPlayerManager.getInstance().isBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoPlayerManager.getInstance().onDestroy();
        //如果你的Activity是MainActivity并且你开启过悬浮窗口播放器，则还需要对其释放
        VideoWindowManager.getInstance().onDestroy();
    }
}
