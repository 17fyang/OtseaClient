package com.stu.otseaclient.component.adapter;

import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.pojo.PostInfo;
import com.stu.otseaclient.util.DateUtil;
import com.stu.otseaclient.util.UrlUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/2/25 0:07
 * @Description:
 */
public class PostDiscoveryAdapter extends BaseListAdapter<PostInfo> {


    public PostDiscoveryAdapter(List<PostInfo> itemData, int layoutResource) {
        super(itemData, layoutResource);
    }

    @Override
    public void bindView(ViewHolder holder, PostInfo item) {
        TextView authorName = holder.getItem().findViewById(R.id.post_author_name);
        TextView contentTextView = holder.getItem().findViewById(R.id.post_info_content);
        TextView postTimeView = holder.getItem().findViewById(R.id.post_info_time);
        NetImageView headImage = holder.getItem().findViewById(R.id.post_author_image);

        String headUrl = UrlUtil.absFile(item.getAuthorInfo().getHeadImage());
        headImage.setImageURL(headUrl);
        authorName.setText(item.getAuthorInfo().getName());
        contentTextView.setText(item.getPost().getContent());
        postTimeView.setText(DateUtil.showTime(item.getPost().getCreateTime()));
    }
}
