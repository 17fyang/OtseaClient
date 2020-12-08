package com.stu.otseaclient.activity;


import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.hutool.core.util.StrUtil;
import com.stu.com.R;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.enumreation.RestCode;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.util.MessageUtil;
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
        HttpRequest.getInstance().asyncPost(ApiEnum.VERIFICATION, formBody, (rest) -> {
            if (rest.getCode() != RestCode.SUCCEED) {
                MessageUtil.getInstance().sendToast(rest.getMsg());
            } else {
                MessageUtil.getInstance().switchActivity(LoginActivity.class);
            }
        });
    }

    /**
     * 提交注册信息
     *
     * @param view
     */
    public void handIn(View view) {
        String account = accountEditText.getText().toString();
        if (StrUtil.isEmpty(account)) {
            Toast.makeText(RegisterActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = registerPasswordEditText.getText().toString();
        if (StrUtil.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String confirmPassword = confirmPasswordEditText.getText().toString();
        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        String verificationCode = verificationEditText.getText().toString();
        if (StrUtil.isEmpty(verificationCode)) {
            Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody formBody = new FormBody.Builder()
                .add("mail", account)
                .add("password", password)
                .add("verificationCode", verificationCode).build();

        HttpRequest.getInstance().asyncPost(ApiEnum.USER_REGISTER, formBody, (rest) -> {
            Bundle bundle = new Bundle();
            Message message = new Message();
            message.setData(bundle);
            if (rest.getCode() != RestCode.SUCCEED) {
                message.what = 0x1;
                bundle.putString("info", rest.getMsg());
                GeneralHandle.getInstance().sendMessage(message);
            } else {
                message.what = 0x10;
                GeneralHandle.getInstance().sendMessage(message);
            }
        });

    }
}
