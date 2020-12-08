package com.stu.otseaclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.stu.com.R;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.enumreation.RestCode;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.general.Rest;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends MyBaseActivity {
    CheckBox rememberBox;
    CheckBox autoLoginBox;
    EditText accountEditText;
    EditText passwordEditText;

    /**
     * 登录接口回调处理
     */
    private Handler loginHandle = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Rest restResponse = Rest.valueOfBundle(msg.getData());
            if (restResponse.getCode() == RestCode.SUCCEED) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, restResponse.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rememberBox = findViewById(R.id.remPassword);
        autoLoginBox = findViewById(R.id.autoLogin);
        accountEditText = findViewById(R.id.loginAccount);
        passwordEditText = findViewById(R.id.loginPassword);
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
            Message message = new Message();
            message.setData(rest.packToBundle());
            loginHandle.sendMessage(message);
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
    public void remember(View view) {

    }

    /**
     * 自动登录点击监听
     */
    public void autoLogin(View view) {
        this.autoLoginBox.setOnCheckedChangeListener((buttonView, isCheck) -> {
            this.rememberBox.setChecked(isCheck);
        });
    }
}
