package com.stu.otseaclient.activity.mainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.stu.com.R;
import com.stu.otseaclient.component.adapter.LessonListAdapter;
import com.stu.otseaclient.util.MockUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/18 19:22
 * @Description:
 */
public class LessonFragment extends Fragment {
    private View view;
    private ListView lessonListView;
    private LessonListAdapter lessonListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lesson, container, false);

        lessonListAdapter = new LessonListAdapter(MockUtil.mockLessonInfo(), R.layout.item_lesson_list);
        lessonListView = view.findViewById(R.id.lesson_list_view);
        lessonListView.setAdapter(lessonListAdapter);

        //添加listview
        view.findViewById(R.layout.item_lesson_list);

        return view;
    }
}
