package com.stu.otseaclient.activity.lessonPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.UrlUtil;
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

        NetImageView authorImageView = view.findViewById(R.id.lesson_intro_author_image);
        String headUrl = UrlUtil.absFile(lessonInfo.getAuthorInfo().getHeadImage());
        authorImageView.setImageURL(headUrl);

        TextView nameTextView = view.findViewById(R.id.lesson_intro_author_name);
        nameTextView.setText(lessonInfo.getAuthorInfo().getName());

        TextView introView = view.findViewById(R.id.lesson_intro_author_intro);
        introView.setText(lessonInfo.getAuthorInfo().getIntro());

        TextView contentView = view.findViewById(R.id.lesson_intro_content);
        contentView.setText("\t\t\t\t" + lessonInfo.getLesson().getIntroduce());

        return view;
    }
}
