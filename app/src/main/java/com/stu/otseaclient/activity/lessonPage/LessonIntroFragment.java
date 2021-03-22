package com.stu.otseaclient.activity.lessonPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.activity.PersonIntroItem;
import com.stu.otseaclient.pojo.LessonInfo;
import com.video.player.lib.view.VideoPlayerTrackView;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:30
 * @Description:
 */
public class LessonIntroFragment extends LessonFragment {

    public LessonIntroFragment(VideoPlayerTrackView mVideoPlayer, LessonInfo lessonInfo) {
        super(mVideoPlayer, lessonInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_intro, container, false);

        new PersonIntroItem(view, lessonInfo.getAuthorInfo()).setUp();

        TextView contentView = view.findViewById(R.id.lesson_intro_content);
        contentView.setText("\t\t\t\t" + lessonInfo.getLesson().getIntroduce());

        return view;
    }
}
