package com.stu.otseaclient.component.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.pojo.LessonDirNode;
import com.stu.otseaclient.util.MessageUtil;
import com.stu.otseaclient.util.UrlUtil;
import com.video.player.lib.view.VideoPlayerTrackView;
import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 18:40
 * @Description:
 */
public class LessonDirListAdapter extends BaseListAdapter<LessonDirNode> implements OnSpinnerItemSelectedListener {
    private VideoPlayerTrackView mVideoPlayer;

    public LessonDirListAdapter(VideoPlayerTrackView mVideoPlayer, List<LessonDirNode> itemData, int layoutResource) {
        super(itemData, layoutResource);
        this.mVideoPlayer = mVideoPlayer;
    }


    @Override
    public void bindView(ViewHolder holder, LessonDirNode item) {
        NiceSpinner spinner = holder.getItem().findViewById(R.id.lesson_dir_spinner);
        TextView titleTextView = holder.getItem().findViewById(R.id.lesson_dir_title);
        titleTextView.setText(item.getName());

        spinner.attachDataSource(item.getSon());
        spinner.setOnSpinnerItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
        LessonDirNode node = (LessonDirNode) parent.getSelectedItem();
        Async.run(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("link", UrlUtil.absFile(node.getLink()));
            bundle.putString("title", node.getName());
            MessageUtil.sendBundle(MessageKey.RESET_LESSON_VIDEO, bundle);
        });
    }
}