package com.stu.otseaclient.activity.lessonPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.stu.com.R;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:31
 * @Description:
 */
public class LessonTalkFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_talk, container, false);

        return view;
    }
}