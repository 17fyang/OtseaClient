package com.stu.otseaclient.general;

import android.os.Bundle;

/**
 * @Author: 乌鸦坐飞机亠
 * @CreateDate: 2020/11/12 10:01
 * @Version: 1.0
 * @Description: Restful格式返回封装类
 */
public class Rest {
    private int code;
    private String msg;
    private String data;

    public Rest(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Bundle packToBundle() {
        Bundle restBundle = new Bundle();
        restBundle.putInt("code", this.code);
        restBundle.putString("msg", this.msg);
        restBundle.putString("data", this.data);
        return restBundle;
    }

    public static Rest valueOfBundle(Bundle restBundle) {
        int code = restBundle.getInt("code");
        String msg = restBundle.getString("msg");
        String data = restBundle.getString("data");
        return new Rest(code, msg, data);
    }

    @Override
    public String toString() {
        return "Rest{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}