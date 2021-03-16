package com.stu.otseaclient.activity.lessonPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.stu.com.R;
import com.stu.otseaclient.pojo.LessonInfo;
import com.video.player.lib.view.VideoPlayerTrackView;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:31
 * @Description:
 */
public class LessonTalkFragment extends LessonFragment {
    public LessonTalkFragment(VideoPlayerTrackView mVideoPlayer, LessonInfo lessonInfo) {
        super(mVideoPlayer, lessonInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_talk, container, false);


        //设置点击时清空内容
        EditText editText = view.findViewById(R.id.lesson_talk_input);
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) editText.setText("");
        });

        return view;
    }
}
