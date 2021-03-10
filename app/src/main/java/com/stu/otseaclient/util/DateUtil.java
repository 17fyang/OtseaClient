package com.stu.otseaclient.util;

import android.annotation.SuppressLint;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/8 17:07
 * @Description:
 */
public class DateUtil {
    @SuppressLint("SimpleDateFormat")
    public static final Format format1 = new SimpleDateFormat("yyyy-MM-dd");

    public static String showTime(Date date) {

        long timeInterval = (new Date().getTime() - date.getTime()) / 1000;

        if (timeInterval < 60 * 3) {
            return "刚刚";
        } else if (timeInterval < 60 * 60) {
            return timeInterval / 60 + "分钟前";
        } else if (timeInterval < 60 * 60 * 24) {
            return timeInterval / 60 / 60 + "小时前";
        } else if (timeInterval < 60 * 60 * 24 * 7) {
            return timeInterval / 60 / 60 / 24 + "天前";
        } else {
            return format1.format(date);
        }

    }
}
