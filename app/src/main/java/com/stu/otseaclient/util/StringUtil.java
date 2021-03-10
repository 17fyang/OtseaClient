package com.stu.otseaclient.util;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 15:22
 * @Description:
 */
public class StringUtil {
    public static boolean isNull(String value) {
        return value == null || value.equals("null") || value.equals("NULL");
    }
}
