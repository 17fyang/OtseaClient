package com.stu.otseaclient.component.adapter;

import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.DateUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 14:36
 * @Description:
 */
public class LessonListAdapter extends BaseListAdapter<LessonInfo> {
    public LessonListAdapter(List<LessonInfo> itemData, int layoutResource) {
        super(itemData, layoutResource);
    }

    @Override
    public void bindView(ViewHolder holder, LessonInfo item) {
        NetImageView lessonImageView = holder.getItem().findViewById(R.id.lesson_info_image);
        TextView titleView = holder.getItem().findViewById(R.id.lesson_info_title);
        TextView authorView = holder.getItem().findViewById(R.id.lesson_info_author);
        TextView publishTimeView = holder.getItem().findViewById(R.id.lesson_info_time);

        lessonImageView.setImageURL(ApiEnum.file(item.getTitleImage().getResourcePath()));
        titleView.setText(item.getLesson().getTitle());
        authorView.setText(item.getAuthorInfo().getUserBaseInfo().getName());
        publishTimeView.setText(DateUtil.showTime(item.getLesson().getCreateTime()));
    }
}
