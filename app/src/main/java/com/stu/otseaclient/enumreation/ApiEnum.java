package com.stu.otseaclient.enumreation;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/7 19:11
 * @Description:
 */
public class ApiEnum {
    public static final String HOST = "172.16.139.22";
    public static final int PORT = 80;

    public static final String USER_LOGIN = user("/login");
    public static final String USER_REGISTER = user("/register");
    public static final String VERIFICATION = user("/verification");
    public static final String LIST_DISCOVERY = content("/post/listAction");
    public static final String MY_COLLECT_LESSON = content("/lesson/myCollectLessons");
    public static final String MY_LESSONS = content("/lesson/myLessons");
    public static final String MY_RECORDS = content("/lesson/myRecords");
    public static final String LIST_LESSONS = content("/lesson/listAction");
    public static final String RESOURCE_DETAIL = content("/lesson/resource");
    public static final String LAST_LESSON = content("/lesson/lastWatchLesson");
    public static final String SEARCH_LESSON = content("/lesson/search");

    public static String concat(String url) {
        return "http://" + HOST + ":" + PORT + url;
    }

    public static String user(String url) {
        return concat("/otsea/user" + url);
    }

    public static String content(String url) {
        return concat("/otsea/content" + url);
    }

    public static String file(String url) {
        return concat("/otsea/static" + url);
    }

    public static String head(String url) {
        return file("/head" + url);
    }


}
