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

import java.util.LinkedList;

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

    public LinkedList<LessonInfo> listAction() {
        LinkedList<LessonInfo> list = new LinkedList<>();
        try {
            RequestBody formBody = new FormBody.Builder().build();
            Rest rest = HttpRequest.getInstance().syncPost(ApiEnum.LIST_LESSONS, formBody);
            if (rest.getData().isEmpty()) return list;

            ArrayNode arrayNode = (ArrayNode) rest.getData();

            for (JsonNode node : arrayNode) {
                ObjectNode objectNode = (ObjectNode) node;
                list.add(JsonUtil.getObjectMapper().treeToValue(objectNode, LessonInfo.class));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
