package com.stu.otseaclient.component.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.component.listener.LessonClickListener;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.DateUtil;
import com.stu.otseaclient.util.UrlUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 14:36
 * @Description:
 */
public class LessonListAdapter extends BaseListAdapter<LessonInfo> implements AdapterView.OnItemClickListener {
    private LessonClickListener lessonClickListener;

    public LessonListAdapter(List<LessonInfo> itemData, int layoutResource) {
        super(itemData, layoutResource);
        lessonClickListener = new LessonClickListener();
    }

    @Override
    public void bindView(ViewHolder holder, LessonInfo item) {
        NetImageView lessonImageView = holder.getItem().findViewById(R.id.lesson_info_image);
        TextView titleView = holder.getItem().findViewById(R.id.lesson_info_title);
        TextView authorView = holder.getItem().findViewById(R.id.lesson_info_author);
        TextView publishTimeView = holder.getItem().findViewById(R.id.lesson_info_time);

        String resourcePath = UrlUtil.absFile(item.getTitleImage().getResourcePath());
        lessonImageView.setImageURL(resourcePath);

        titleView.setText(item.getLesson().getTitle());
        authorView.setText(item.getAuthorInfo().getName());
        publishTimeView.setText(DateUtil.showTime(item.getLesson().getCreateTime()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LessonInfo lessonInfo = getItem(position);

        lessonClickListener.setLessonInfo(lessonInfo);
        lessonClickListener.onClick(view);
    }
}
