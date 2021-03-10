package com.stu.otseaclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.stu.com.R;
import com.stu.otseaclient.activity.lessonPage.LessonDetailActivity;
import com.stu.otseaclient.activity.mainPage.MainActivity;
import com.stu.otseaclient.temp.NetTestListener;

public class StartActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button loginStartButton = findViewById(R.id.loginButton);
        loginStartButton.setOnClickListener((view) -> startActivity(new Intent(this, LoginActivity.class)));

        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener((view) -> startActivity(new Intent(this, LessonDetailActivity.class)));

        Button mainButton = findViewById(R.id.mainButton);
        mainButton.setOnClickListener((view) -> startActivity(new Intent(this, MainActivity.class)));

        Button ioTestButton = findViewById(R.id.io_test_button);
        ioTestButton.setOnClickListener(new NetTestListener());
    }
}