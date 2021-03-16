package com.stu.otseaclient.activity.lessonPage;

import androidx.fragment.app.Fragment;
import com.stu.otseaclient.pojo.LessonInfo;
import com.video.player.lib.view.VideoPlayerTrackView;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/11 16:14
 * @Description:
 */
public class LessonFragment extends Fragment {
    protected VideoPlayerTrackView mVideoPlayer;
    protected LessonInfo lessonInfo;

    public LessonFragment(VideoPlayerTrackView mVideoPlayer, LessonInfo lessonInfo) {
        this.mVideoPlayer = mVideoPlayer;
        this.lessonInfo = lessonInfo;
    }
    
}
