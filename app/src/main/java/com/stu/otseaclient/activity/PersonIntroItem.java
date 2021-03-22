package com.stu.otseaclient.activity;

import android.view.View;
import android.widget.TextView;
import com.stu.com.R;
import com.stu.otseaclient.component.image.NetImageView;
import com.stu.otseaclient.pojo.UserInfo;
import com.stu.otseaclient.util.UrlUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/21 18:47
 * @Description:
 */
public class PersonIntroItem {
    private View view;
    private UserInfo userInfo;

    public PersonIntroItem(View view, UserInfo userInfo) {
        this.view = view;
        this.userInfo = userInfo;
    }

    public void setUp() {
        NetImageView authorImageView = view.findViewById(R.id.lesson_intro_author_image);
        String headUrl = UrlUtil.absFile(userInfo.getHeadImage());
        authorImageView.setImageURL(headUrl);

        TextView nameTextView = view.findViewById(R.id.lesson_intro_author_name);
        nameTextView.setText(userInfo.getName());

        TextView introView = view.findViewById(R.id.lesson_intro_author_intro);
        introView.setText(userInfo.getIntro());
    }
}
