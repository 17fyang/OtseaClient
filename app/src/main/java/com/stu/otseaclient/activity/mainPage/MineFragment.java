package com.stu.otseaclient.activity.mainPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stu.com.R;
import com.stu.otseaclient.activity.LessonSearchActivity;
import com.stu.otseaclient.activity.LoginActivity;
import com.stu.otseaclient.activity.PersonIntroItem;
import com.stu.otseaclient.component.BaseFragment;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.component.listener.LessonClickListener;
import com.stu.otseaclient.controller.LessonController;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.pojo.UserInfo;
import com.stu.otseaclient.util.*;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/18 19:23
 * @Description:
 */
public class MineFragment extends BaseFragment {
    private View view;
    private NetImageView lastLessonImage;
    private TextView lastLessonTitle;
    private TextView lastLessonAuthor;
    private TextView lastLessonTime;
    private RelativeLayout lastWatchLayout;
    private RelativeLayout mineHadLoginLayout;
    private RelativeLayout mineUnLoginLayout;

    {
        //注册刷新上次看到的课程信息handle
        GeneralHandle.getInstance().registerHandle(MessageKey.REFRESH_LAST_LESSON, (ctx, msg) -> {
            if (!(ctx instanceof MainActivity)) return;
            MineFragment mineFragment = ((MainActivity) ctx).getMineFragment();
            LessonInfo lessonInfo = (LessonInfo) msg.obj;
            mineFragment.lastWatchLayout.setOnClickListener(new LessonClickListener(lessonInfo));
            mineFragment.setUpLastLessonInfo(lessonInfo);
        });
    }

    public MineFragment(Activity activity) {
        super(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);

        lastLessonImage = view.findViewById(R.id.lesson_info_image);
        lastLessonTitle = view.findViewById(R.id.lesson_info_title);
        lastLessonAuthor = view.findViewById(R.id.lesson_info_author);
        lastLessonTime = view.findViewById(R.id.lesson_info_time);
        lastWatchLayout = view.findViewById(R.id.mine_last_watch_layout);
        mineHadLoginLayout = view.findViewById(R.id.mine_had_login_layout);
        mineUnLoginLayout = view.findViewById(R.id.mine_unLogin_layout);

        Async.run(() -> {
            LessonInfo lessonInfo = LessonController.getInstance().lastWatchLesson();

            Message msg = new Message();
            msg.what = MessageKey.REFRESH_LAST_LESSON;
            msg.obj = lessonInfo;
            MessageUtil.sendMessage(msg);
        });

        mineUnLoginLayout.setOnClickListener((v) -> {
            Intent intent = new Intent(activity, LoginActivity.class);
            startActivity(intent);
        });

        RelativeLayout myLessonLayout = view.findViewById(R.id.mine_my_lesson_layout);
        myLessonLayout.setOnClickListener(new MineFragment.TopClickListener((MainActivity) activity, LessonSearchActivity.FLAG_MY));

        RelativeLayout myCollectLayout = view.findViewById(R.id.mine_collect_layout);
        myCollectLayout.setOnClickListener(new MineFragment.TopClickListener((MainActivity) activity, LessonSearchActivity.FLAG_COLLECT));

        RelativeLayout recordLayout = view.findViewById(R.id.mine_record_layout);
        recordLayout.setOnClickListener(new MineFragment.TopClickListener((MainActivity) activity, LessonSearchActivity.FLAG_RECORD));

        return view;
    }

    static class TopClickListener implements View.OnClickListener {
        private final int flag;
        private final MainActivity mainActivity;

        public TopClickListener(MainActivity activity, int flag) {
            this.mainActivity = activity;
            this.flag = flag;
        }

        @Override
        public void onClick(View v) {
            if (!mainActivity.isLogin())
                UiUtil.sendToast("未登录！");
            else {
                Intent intent = new Intent(mainActivity, LessonSearchActivity.class);
                intent.addFlags(flag);
                mainActivity.startActivity(intent);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //初始化登录状态
        Intent intent = activity.getIntent();
        if (!intent.hasExtra("login")) {
            setLoginStatus(false, null);
        } else {
            byte[] value = intent.getByteArrayExtra("login");
            ObjectNode node = (ObjectNode) JsonUtil.readTree(value);
            String token = node.get("token").asText();
            HttpRequest.getInstance().setToken(token);

            UserInfo userInfo = JsonUtil.treeToValue(node.get("userInfoVo"), UserInfo.class);

            setLoginStatus(true, userInfo);
        }
    }

    public void setLoginStatus(boolean isLogin, UserInfo userInfo) {
        ((MainActivity) activity).setLogin(isLogin);
        if (view == null) return;
        if (isLogin) {
            mineUnLoginLayout.setVisibility(View.INVISIBLE);
            mineHadLoginLayout.setVisibility(View.VISIBLE);
            new PersonIntroItem(view, userInfo).setUp();
        } else {
            mineUnLoginLayout.setVisibility(View.VISIBLE);
            mineHadLoginLayout.setVisibility(View.INVISIBLE);
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
