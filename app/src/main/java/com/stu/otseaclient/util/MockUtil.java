package com.stu.otseaclient.util;

import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.pojo.LessonInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 14:51
 * @Description:
 */
public class MockUtil {
    public static List<LessonInfo> mockLessonInfo() {
        List<LessonInfo> list = new ArrayList<>();
        list.add(new LessonInfo(ApiEnum.concat("/lesson/continue.jpg"), "这是测试名字1", "这是测试作者1"));
        list.add(new LessonInfo(ApiEnum.concat("/lesson/continue.jpg"), "这是测试名字2", "这是测试作者2"));
        list.add(new LessonInfo(ApiEnum.concat("/lesson/continue.jpg"), "这是测试名字3", "这是测试作者3"));
        list.add(new LessonInfo(ApiEnum.concat("/lesson/continue.jpg"), "这是测试名字4", "这是测试作者4"));
        list.add(new LessonInfo(ApiEnum.concat("/lesson/continue.jpg"), "这是测试名字5", "这是测试作者5"));
        return list;
    }
}
