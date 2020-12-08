package com.stu.otseaclient.general;


/**
 * @Author: 乌鸦坐飞机亠
 * @CreateDate: 2020/11/12 10:02
 * @Version: 1.0
 * @Description: restful状态码
 */
public enum RestCode {
    SUCCEED(200),
    UNKNOWN(500),
    DATA_FORMAT_EXCEPTION(511),
    DATA_CONTENT_EXCEPTION(510);


    private final int code;

    RestCode(int code) {
        this.code = code;
    }

    public static RestCode valueOfCode(int code) {
        for (RestCode restCode : RestCode.values()) {
            if (restCode.code == code) return restCode;
        }
        return RestCode.UNKNOWN;
    }


    public int getCode() {
        return code;
    }
}
