package com.stu.otseaclient.general;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/7 19:11
 * @Description:
 */
public class ApiEnum {
    public static final String HOST = "172.16.66.109";
    public static final int PORT = 8100;

    public static final String USER_LOGIN = concat("/otsea/user/login");
    public static final String USER_REGISTER = concat("/otsea/user/register");

    private static String concat(String url) {
        return "http://" + HOST + ":" + PORT + url;
    }

}
