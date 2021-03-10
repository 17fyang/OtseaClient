package com.stu.otseaclient.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.general.Rest;
import com.stu.otseaclient.pojo.PostInfo;
import com.stu.otseaclient.util.JsonUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.util.LinkedList;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 15:28
 * @Description:
 */
public class PostController {
    public static final PostController instance = new PostController();

    public static PostController getInstance() {
        return instance;
    }


    public LinkedList<PostInfo> listPost() {
        LinkedList<PostInfo> postInfoList = new LinkedList<>();
        try {
            RequestBody formBody = new FormBody.Builder().build();
            Rest rest = HttpRequest.getInstance().syncPost(ApiEnum.LIST_DISCOVERY, formBody);
            if (rest.getData().isEmpty()) return postInfoList;

            ArrayNode arrayNode = (ArrayNode) rest.getData();

            for (JsonNode node : arrayNode) {
                ObjectNode objectNode = (ObjectNode) node;
                postInfoList.add(JsonUtil.getObjectMapper().treeToValue(objectNode, PostInfo.class));
            }
            return postInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return postInfoList;
        }
    }
}
