package com.stu.otseaclient.util;

import android.net.Uri;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 14:48
 * @Description:
 */
public class UriUtil {

    /**
     * 获取某个uir地址对应的资源
     *
     * @param uriLocation
     * @return
     */
    public static Uri parse(String uriLocation) {
        //todo 后续改成先检查本地缓存的方式
        return Uri.parse(uriLocation);
    }

    public static String parseCache(String uriLocation) {
        //todo 后续改成先检查本地缓存的方式
        return uriLocation;
    }
}
