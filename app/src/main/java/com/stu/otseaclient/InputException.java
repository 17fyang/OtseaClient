package com.stu.otseaclient;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/8 20:53
 * @Description:
 */
public class InputException extends RuntimeException {
    private String msg;

    public InputException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
