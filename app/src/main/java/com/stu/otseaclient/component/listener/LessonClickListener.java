package com.stu.otseaclient.component.listener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.stu.otseaclient.activity.lessonPage.LessonDetailActivity;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.JsonUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/16 20:43
 * @Description:
 */
public class LessonClickListener implements View.OnClickListener {
    private volatile LessonInfo lessonInfo;

    public LessonClickListener() {

    }

    public LessonClickListener(LessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(GeneralHandle.getInstance().getCurContext(), LessonDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putByteArray("lessonInfo", JsonUtil.writeAsByte(lessonInfo));
        intent.putExtra("lessonInfo", bundle);

        //跳转到lessonDetailActivity
        GeneralHandle.getInstance().getCurContext().startActivity(intent);
    }

    public LessonInfo getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(LessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }
}
