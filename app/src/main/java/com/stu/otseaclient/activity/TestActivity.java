package com.stu.otseaclient.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import com.stu.com.R;
import com.stu.otseaclient.enumreation.ApiEnum;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/10 14:31
 * @Description:
 */
public class TestActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        VideoView videoView = findViewById(R.id.videoView);

        //加载指定的视频文件
        String path = ApiEnum.concat("/resource/test.mp4");

        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);
        

        videoView.setVideoURI(Uri.parse(path));
    }
}
