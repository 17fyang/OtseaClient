package com.stu.otseaclient.util;

import com.stu.otseaclient.enumreation.ApiEnum;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 19:37
 * @Description:
 */
public class UrlUtil {

    public static String absFile(String image) {
        if (validate(image)) return image;
        return ApiEnum.file(image);
    }

    private static boolean validate(String url) {
        return url.startsWith("http") || url.startsWith("blob");
    }
}
