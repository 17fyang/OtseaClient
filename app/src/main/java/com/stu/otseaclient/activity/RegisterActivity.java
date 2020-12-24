package com.stu.otseaclient.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.hutool.core.util.StrUtil;
import com.stu.com.R;
import com.stu.otseaclient.InputException;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.enumreation.RestCode;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.util.UiUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends MyBaseActivity {
    private EditText accountEditText;
    private EditText registerPasswordEditText;
    private EditText confirmPasswordEditText;
    private EditText verificationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountEditText = findViewById(R.id.registerEmail);
        registerPasswordEditText = findViewById(R.id.registerPassword);
        confirmPasswordEditText = findViewById(R.id.registerPassword2);
        verificationEditText = findViewById(R.id.registerVerCode);
    }


    /**
     * 获取验证码
     *
     * @param view
     */
    public void verification(View view) {
        String account = accountEditText.getText().toString();
        if (StrUtil.isEmpty(account)) {
            Toast.makeText(RegisterActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestBody formBody = new FormBody.Builder().add("mail", account).build();
        HttpRequest.getInstance().asyncPostRest(ApiEnum.VERIFICATION, formBody, (rest) -> {
            if (rest.getCode() != RestCode.SUCCEED) {
                UiUtil.sendToast(rest.getMsg());
            } else {
                UiUtil.sendToast("验证码已发送");
            }
        });
    }

    /**
     * 提交注册信息
     *
     * @param view
     */
    public void handIn(View view) {
        try {
            String account = accountEditText.getText().toString();
            if (StrUtil.isEmpty(account)) throw new InputException("账号不能为空");
            String password = registerPasswordEditText.getText().toString();
            if (StrUtil.isEmpty(password)) throw new InputException("密码不能为空");
            String confirmPassword = confirmPasswordEditText.getText().toString();
            if (!password.equals(confirmPassword)) throw new InputException("两次输入密码不一致");
            String verificationCode = verificationEditText.getText().toString();
            if (StrUtil.isEmpty(verificationCode)) throw new InputException("验证码不能为空");

            RequestBody formBody = new FormBody.Builder()
                    .add("mail", account)
                    .add("password", password)
                    .add("verificationCode", verificationCode).build();

            HttpRequest.getInstance().asyncPostRest(ApiEnum.USER_REGISTER, formBody, (rest) -> {
                if (rest.getCode() != RestCode.SUCCEED) {
                    UiUtil.sendToast(rest.getMsg());
                } else {
                    UiUtil.sendToast("注册成功！");
                    UiUtil.switchActivity(LoginActivity.class);
                }
            });
        } catch (InputException e) {
            Toast.makeText(RegisterActivity.this, e.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
}
