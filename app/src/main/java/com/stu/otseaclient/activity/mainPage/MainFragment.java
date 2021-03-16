package com.stu.otseaclient.activity.mainPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.stu.com.R;
import com.stu.otseaclient.activity.LessonSearchActivity;
import com.stu.otseaclient.component.adapter.LessonListAdapter;
import com.stu.otseaclient.component.image.GlideImageLoader;
import com.stu.otseaclient.component.listener.SearchClickListener;
import com.stu.otseaclient.controller.LessonController;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.MessageUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/16 21:27
 * @Description:
 */
public class MainFragment extends Fragment {
    private static String[][] TYPE_CONFIG = new String[][]{
            {"四六级", "计算机", "心理学", "考研", "经济"},
            {"音乐", "公开", "热门", "数学", "课程"}};


    private ListView mainListView;
    private LessonListAdapter lessonListAdapter;
    private List<LessonInfo> lessonInfoList;
    private View view;
    private RelativeLayout mainSearchLayout;
    private SearchView searchView;

    {
        //注册主界面刷新listview的handler
        GeneralHandle.getInstance().registerHandle(MessageKey.REFRESH_MAIN_LESSON_LIST, (ctx, msg) -> {
            lessonListAdapter.setItemData(this.lessonInfoList);
            lessonListAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        Async.run(() -> {
            lessonInfoList = LessonController.getInstance().listAction();
            MessageUtil.sendEmptyMessage(MessageKey.REFRESH_MAIN_LESSON_LIST);
        });

        initBanner();

        lessonListAdapter = new LessonListAdapter(lessonInfoList, R.layout.item_lesson_list);
        mainListView = view.findViewById(R.id.main_lesson_list);
        mainListView.setAdapter(lessonListAdapter);

        //设置点击监听
        mainListView.setOnItemClickListener(lessonListAdapter);

        //设置搜索框
        searchView = view.findViewById(R.id.search_click);
        searchView.setOnQueryTextListener(new SearchClickListener());

        LinearLayout[] typeLine = new LinearLayout[]
                {view.findViewById(R.id.main_type_layout1),
                        view.findViewById(R.id.main_type_layout2)};

        for (int i = 0; i < typeLine.length; i++) {
            for (int j = 0; j < typeLine[i].getChildCount(); j++) {
                ImageButton typeImage = (ImageButton) typeLine[i].getChildAt(j);
                String searchKey = TYPE_CONFIG[i][j];
                typeImage.setOnClickListener((v) -> {
                    Intent intent = new Intent(GeneralHandle.getInstance().getCurContext(), LessonSearchActivity.class);
                    intent.putExtra("search", searchKey);

                    GeneralHandle.getInstance().getCurContext().startActivity(intent);
                });
            }
        }


        return view;
    }

    /**
     * 初始化banner轮播图
     */
    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner_1);
        images.add(R.drawable.banner_2);
        images.add(R.drawable.banner_3);
        images.add(R.drawable.banner_4);

        Banner banner = view.findViewById(R.id.main_banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播时间
        banner.setDelayTime(3500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
