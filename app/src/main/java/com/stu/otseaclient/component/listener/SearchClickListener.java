package com.stu.otseaclient.component.listener;

import android.content.Intent;
import android.widget.SearchView;
import com.stu.otseaclient.activity.LessonSearchActivity;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.util.StringUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/16 18:57
 * @Description:
 */
public class SearchClickListener implements SearchView.OnQueryTextListener {


    @Override
    public boolean onQueryTextSubmit(String query) {
        if (StringUtil.isNull(query)) return true;

        Intent intent = new Intent(GeneralHandle.getInstance().getCurContext(), LessonSearchActivity.class);
        intent.putExtra("search", query);

        GeneralHandle.getInstance().getCurContext().startActivity(intent);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
