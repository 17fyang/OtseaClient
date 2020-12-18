package com.stu.otseaclient.enumreation;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/7 19:11
 * @Description:
 */
public class ApiEnum {
    public static final String HOST = "120.79.175.145";
    public static final int PORT = 8100;

    public static final String USER_LOGIN = concat("/otsea/user/login");
    public static final String USER_REGISTER = concat("/otsea/user/register");
    public static final String VERIFICATION = concat("/otsea/user/verification");

    public static String concat(String url) {
        return "http://" + HOST + ":" + PORT + url;
    }

}
