package com.stu.otseaclient.activity.mainPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewpager.widget.ViewPager;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stu.com.R;
import com.stu.otseaclient.activity.MyBaseActivity;
import com.stu.otseaclient.pojo.UserInfo;
import com.stu.otseaclient.util.JsonUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/16 20:16
 * @Description:
 */
public class MainActivity extends MyBaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    public static final int MAIN_FRAGMENT = 0;
    public static final int DISCOVERY_FRAGMENT = 1;
    public static final int LESSON_FRAGMENT = 2;
    public static final int MINE_FRAGMENT = 3;

    private MyFragmentPageAdapter pagerAdapter;
    private ViewPager viewPager;
    private RadioButton[] radioButtons = new RadioButton[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerAdapter = new MyFragmentPageAdapter(getSupportFragmentManager(), 0);

        //设置adapter
        viewPager = findViewById(R.id.main_view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(MAIN_FRAGMENT);
        viewPager.addOnPageChangeListener(this);

        //设置radioGroup点击监听
        RadioGroup radioGroup = findViewById(R.id.rg_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);

        //设置radioGroup的几个button
        radioButtons[MAIN_FRAGMENT] = findViewById(R.id.rb_main);
        radioButtons[DISCOVERY_FRAGMENT] = findViewById(R.id.rb_discovery);
        radioButtons[LESSON_FRAGMENT] = findViewById(R.id.rb_lesson);
        radioButtons[MINE_FRAGMENT] = findViewById(R.id.rb_mine);

        //默认的页面是首页
        radioButtons[MAIN_FRAGMENT].setChecked(true);
        
    }

    @Override
    public void onResume() {
        //初始化登录状态
        initLogin();
    }

    /**
     * 初始化登录状态
     */
    private void initLogin() {
        Intent intent = getIntent();
        if (!intent.hasExtra("login")) return;

        byte[] value = intent.getByteArrayExtra("login");
        ObjectNode node = (ObjectNode) JsonUtil.readTree(value);
        String token = node.get("token").asText();
        UserInfo userInfo = JsonUtil.treeToValue(node.get("userInfoVo"), UserInfo.class);

        getMineFragment().setLoginStatus(true, userInfo);
    }

    /**
     * radioGroup切换监听
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main:
                viewPager.setCurrentItem(MAIN_FRAGMENT, true);
                break;
            case R.id.rb_discovery:
                viewPager.setCurrentItem(DISCOVERY_FRAGMENT, true);
                break;
            case R.id.rb_lesson:
                viewPager.setCurrentItem(LESSON_FRAGMENT, true);
                break;
            case R.id.rb_mine:
                viewPager.setCurrentItem(MINE_FRAGMENT, true);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //viewpage移动监听，state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2)
            radioButtons[viewPager.getCurrentItem()].setChecked(true);
    }

    public MineFragment getMineFragment() {
        return (MineFragment) pagerAdapter.getFragment(MINE_FRAGMENT);
    }

}
