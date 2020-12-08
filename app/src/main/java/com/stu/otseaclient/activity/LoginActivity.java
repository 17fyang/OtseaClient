package com.stu.otseaclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.stu.com.R;
import com.stu.otseaclient.general.ApiEnum;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.general.RestCode;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity {
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


        this.setRememberListener();
        this.setAutoLogin();

    }

    /**
     * 登录按钮点击监听
     *
     * @param view
     */
    public void login(View view) {
        RequestBody formBody = new FormBody.Builder()
                .add("mail", accountEditText.getText().toString())
                .add("password", passwordEditText.getText().toString())
                .build();

        HttpRequest.getInstance().asyncPost(ApiEnum.USER_LOGIN, formBody, (rest) -> {
            //处理登录
            if (rest.getCode() == RestCode.SUCCEED)
                Toast.makeText(this, "登录成功", Toast.LENGTH_LONG);
            else
                Toast.makeText(this, rest.getMsg(), Toast.LENGTH_LONG);
        });
    }

    /**
     * 忘记密码点击监听
     *
     * @param view
     */
    public void forgetPassword(View view) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
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
     * 记住密码点击监听
     */
    public void setRememberListener() {

    }

    /**
     * 自动登录点击监听
     */
    public void setAutoLogin() {
        this.autoLoginBox.setOnCheckedChangeListener((buttonView, isCheck) -> {
            this.rememberBox.setChecked(isCheck);
        });
    }
}
