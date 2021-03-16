package com.stu.otseaclient.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.general.Rest;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.JsonUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/8 16:36
 * @Description:
 */
public class LessonController {
    public static final LessonController instance = new LessonController();

    public static LessonController getInstance() {
        return instance;
    }

    /**
     * 获取推荐课程列表
     *
     * @return
     */
    public List<LessonInfo> listAction() {
        RequestBody formBody = new FormBody.Builder().build();
        Rest rest = HttpRequest.getInstance().syncPost(ApiEnum.LIST_LESSONS, formBody);

        return arrayNodeToLessonInfoList((ArrayNode) rest.getData());
    }

    /**
     * 获取上次看到的课程
     *
     * @return
     */
    public LessonInfo lastWatchLesson() {
        RequestBody formBody = new FormBody.Builder().build();
        Rest rest = HttpRequest.getInstance().syncPost(ApiEnum.LAST_LESSON, formBody);

        return JsonUtil.treeToValue(rest.getData(), LessonInfo.class);
    }

    /**
     * 搜素课程
     *
     * @param key
     * @return
     */
    public List<LessonInfo> searchLessons(String key) {
        RequestBody formBody = new FormBody.Builder().add("key", key).build();
        Rest rest = HttpRequest.getInstance().syncPost(ApiEnum.SEARCH_LESSON, formBody);

        return arrayNodeToLessonInfoList((ArrayNode) rest.getData());
    }

    private List<LessonInfo> arrayNodeToLessonInfoList(ArrayNode arrayNode) {
        List<LessonInfo> list = new ArrayList<>();
        for (JsonNode node : arrayNode) {
            ObjectNode objectNode = (ObjectNode) node;
            list.add(JsonUtil.treeToValue(objectNode, LessonInfo.class));
        }
        return list;
    }
}
