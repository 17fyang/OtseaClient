package com.stu.otseaclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.stu.com.R;

public class MainActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginStartButton = findViewById(R.id.loginButton);
        loginStartButton.setOnClickListener((view) -> startActivity(new Intent(this, LoginActivity.class)));
    }
}