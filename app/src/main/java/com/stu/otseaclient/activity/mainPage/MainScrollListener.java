package com.stu.otseaclient.activity.mainPage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/12 19:44
 * @Description:
 */
public class MainScrollListener implements AbsListView.OnScrollListener, View.OnTouchListener {
    private float lastLocationY = -1f;
    private boolean canUp = true;
    private boolean canDown = false;
    private boolean scrollFlag = false;
    private boolean isUp = false;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                scrollFlag = false;
                lastLocationY = -1;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                scrollFlag = true;


                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (scrollFlag) {

            if (isUp) {
                //如果是上滑
                float nowY = view.getY();
                if (nowY > 130)
                    view.setY(view.getY() - 8);
            }

        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 触摸按下时的操作
                scrollFlag = true;
                lastLocationY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 触摸移动时的操作
                float nowY = event.getY();
                isUp = nowY < lastLocationY;
                lastLocationY = nowY;
                break;
        }
        return false;
    }
}
