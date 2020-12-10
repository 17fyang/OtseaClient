package com.stu.otseaclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import cn.hutool.core.util.StrUtil;
import com.stu.com.R;
import com.stu.otseaclient.InputException;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.enumreation.RestCode;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.util.MessageUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends MyBaseActivity {
    CheckBox rememberBox;
    CheckBox autoLoginBox;
    EditText accountEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rememberBox = findViewById(R.id.remPassword);
        autoLoginBox = findViewById(R.id.autoLogin);
        accountEditText = findViewById(R.id.loginAccount);
        passwordEditText = findViewById(R.id.loginPassword);

        this.setAutoLoginListener();
    }

    /**
     * 登录按钮点击监听
     *
     * @param view
     */
    public void login(View view) {
        try {
            String account = accountEditText.getText().toString();
            if (StrUtil.isEmpty(account)) throw new InputException("用户名不得为空");
            String password = accountEditText.getText().toString();
            if (StrUtil.isEmpty(password)) throw new InputException("用户密码不得为空");

            RequestBody formBody = new FormBody.Builder()
                    .add("mail", account)
                    .add("password", password)
                    .build();

            HttpRequest.getInstance().asyncPost(ApiEnum.USER_LOGIN, formBody, (rest) -> {
                if (rest.getCode() == RestCode.SUCCEED) {
                    MessageUtil.getInstance().sendToast(rest.getMsg());
                } else {
                    MessageUtil.getInstance().sendToast(rest.getMsg());
                }
            });
        } catch (InputException e) {
            Toast.makeText(LoginActivity.this, e.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 忘记密码点击监听
     *
     * @param view
     */
    public void forgetPassword(View view) {
    }

    /**
     * 注册点击监听
     *
     * @param view
     */
    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


    /**
     * 自动登录点击监听
     */
    public void setAutoLoginListener() {
        this.autoLoginBox.setOnCheckedChangeListener((buttonView, isCheck) -> {
            this.rememberBox.setChecked(isCheck);
        });
    }
}
