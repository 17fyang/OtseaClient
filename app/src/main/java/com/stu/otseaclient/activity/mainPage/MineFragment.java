package com.stu.otseaclient.activity.mainPage;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.stu.com.R;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.component.listener.LessonClickListener;
import com.stu.otseaclient.controller.LessonController;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.pojo.UserInfo;
import com.stu.otseaclient.util.DateUtil;
import com.stu.otseaclient.util.MessageUtil;
import com.stu.otseaclient.util.UrlUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/18 19:23
 * @Description:
 */
public class MineFragment extends Fragment {
    private NetImageView lastLessonImage;
    private TextView lastLessonTitle;
    private TextView lastLessonAuthor;
    private TextView lastLessonTime;
    private RelativeLayout lastWatchLayout;

    {
        //注册刷新上次看到的课程信息handle
        GeneralHandle.getInstance().registerHandle(MessageKey.REFRESH_LAST_LESSON, (ctx, msg) -> {
            MineFragment mineFragment = ((MainActivity) ctx).getMineFragment();
            LessonInfo lessonInfo = (LessonInfo) msg.obj;
            mineFragment.lastWatchLayout.setOnClickListener(new LessonClickListener(lessonInfo));
            mineFragment.setUpLastLessonInfo(lessonInfo);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        lastLessonImage = view.findViewById(R.id.lesson_info_image);
        lastLessonTitle = view.findViewById(R.id.lesson_info_title);
        lastLessonAuthor = view.findViewById(R.id.lesson_info_author);
        lastLessonTime = view.findViewById(R.id.lesson_info_time);
        lastWatchLayout = view.findViewById(R.id.mine_last_watch_layout);

        Async.run(() -> {
            LessonInfo lessonInfo = LessonController.getInstance().lastWatchLesson();

            Message msg = new Message();
            msg.what = MessageKey.REFRESH_LAST_LESSON;
            msg.obj = lessonInfo;
            MessageUtil.sendMessage(msg);

        });

        return view;
    }

    public void setLoginStatus(boolean status, UserInfo userInfo) {
        if (status) {

        } else {

        }
    }

    public void setUpLastLessonInfo(LessonInfo lessonInfo) {
        String absPath = UrlUtil.absFile(lessonInfo.getTitleImage().getResourcePath());
        lastLessonImage.setImageURL(absPath);
        lastLessonTitle.setText(lessonInfo.getLesson().getTitle());
        lastLessonAuthor.setText(lessonInfo.getAuthorInfo().getName());
        lastLessonTime.setText(DateUtil.showTime(lessonInfo.getLesson().getCreateTime()));
    }

}
