package com.stu.otseaclient.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import com.stu.com.R;
import com.stu.otseaclient.component.adapter.LessonListAdapter;
import com.stu.otseaclient.component.listener.SearchClickListener;
import com.stu.otseaclient.controller.LessonController;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.MessageUtil;
import com.stu.otseaclient.util.StringUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/16 17:47
 * @Description:
 */
public class LessonSearchActivity extends MyBaseActivity {
    public static final int FLAG_COLLECT = 0b1;
    public static final int FLAG_MY = 0b10;
    public static final int FLAG_RECORD = 0b100;
    public static final int FLAG_LETTER = 0b1000;

    private ListView lessonSearchListView;
    private LessonListAdapter lessonListAdapter;
    private volatile List<LessonInfo> lessonInfoList;
    private SearchView searchView;

    {
        //取得查询的结果
        GeneralHandle.getInstance().registerHandle(MessageKey.GET_SEARCH_LESSON_LIST, (ctx, msg) -> {
            LessonSearchActivity currentContext = (LessonSearchActivity) ctx;
            currentContext.lessonListAdapter.setItemData(currentContext.lessonInfoList);
            currentContext.lessonListAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lesson_list);

        lessonListAdapter = new LessonListAdapter(lessonInfoList, R.layout.item_lesson_list);
        lessonSearchListView = findViewById(R.id.lesson_search_list_view);
        lessonSearchListView.setAdapter(lessonListAdapter);
        lessonSearchListView.setOnItemClickListener(lessonListAdapter);

        //设置搜索框
        searchView = findViewById(R.id.search_click);
        searchView.setOnQueryTextListener(new SearchClickListener());

    }

    @Override
    protected void onResume() {
        super.onResume();
        int flag = getIntent().getFlags();
        String searchContent = getIntent().getStringExtra("search");
        if (!StringUtil.isNull(searchContent)) {
            Async.run(() -> {
                this.lessonInfoList = LessonController.getInstance().searchLessons(searchContent);
                MessageUtil.sendEmptyMessage(MessageKey.GET_SEARCH_LESSON_LIST);
            });
        } else if ((flag & FLAG_COLLECT) != 0) {
            Async.run(() -> {
                this.lessonInfoList = LessonController.getInstance().listMyCollect();
                MessageUtil.sendEmptyMessage(MessageKey.GET_SEARCH_LESSON_LIST);
            });
        } else if ((flag & FLAG_LETTER) != 0) {
            //todo 待实现
        } else if ((flag & FLAG_MY) != 0) {
            Async.run(() -> {
                this.lessonInfoList = LessonController.getInstance().listMyLessons();
                MessageUtil.sendEmptyMessage(MessageKey.GET_SEARCH_LESSON_LIST);
            });
        } else if ((flag & FLAG_RECORD) != 0) {
            Async.run(() -> {
                this.lessonInfoList = LessonController.getInstance().listMyRecord();
                MessageUtil.sendEmptyMessage(MessageKey.GET_SEARCH_LESSON_LIST);
            });
        }

    }
}
