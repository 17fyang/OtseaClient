package com.stu.otseaclient.activity.mainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.stu.com.R;
import com.stu.otseaclient.component.adapter.LessonListAdapter;
import com.stu.otseaclient.controller.LessonController;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.MessageUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/18 19:22
 * @Description:
 */
public class LessonFragment extends Fragment {
    public static final String LESSON_NUM_TEMPLATE = "我的课程 %d";
    private View view;
    private ListView lessonListView;
    private LessonListAdapter lessonListAdapter;
    private List<LessonInfo> lessonInfoList;
    private TextView lessonNumTextView;

    {
        //注册课程界面刷新listview的handler
        GeneralHandle.getInstance().registerHandle(MessageKey.REFRESH_MY_LESSON_LIST, (ctx, msg) -> {
            refresh();
            lessonListAdapter.setItemData(this.lessonInfoList);
            lessonListAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lesson, container, false);

        Async.run(() -> {
            lessonInfoList = LessonController.getInstance().listAction();
            MessageUtil.sendEmptyMessage(MessageKey.REFRESH_MY_LESSON_LIST);
        });

        lessonNumTextView = view.findViewById(R.id.mine_lesson_num);
        lessonListAdapter = new LessonListAdapter(lessonInfoList, R.layout.item_lesson_list);
        lessonListView = view.findViewById(R.id.lesson_list_view);
        lessonListView.setAdapter(lessonListAdapter);

        //刷新界面的其他信息
        refresh();

        return view;
    }

    public void refresh() {
        int listSize = lessonInfoList == null ? 0 : lessonInfoList.size();
        lessonNumTextView.setText(String.format(LESSON_NUM_TEMPLATE, listSize));
    }
}
