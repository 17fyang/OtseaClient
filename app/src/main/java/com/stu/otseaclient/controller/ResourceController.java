package com.stu.otseaclient.controller;

import com.stu.otseaclient.enumreation.ApiEnum;
import com.stu.otseaclient.general.HttpRequest;
import com.stu.otseaclient.general.Rest;
import com.stu.otseaclient.pojo.Resource;
import com.stu.otseaclient.util.JsonUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/11 16:48
 * @Description:
 */
public class ResourceController {
    private static ResourceController instance = new ResourceController();

    public static ResourceController getInstance() {
        return instance;
    }

    public Resource getResourceById(int id) {
        RequestBody formBody = new FormBody.Builder().add("id", String.valueOf(id)).build();
        Rest rest = HttpRequest.getInstance().syncPost(ApiEnum.RESOURCE_DETAIL, formBody);
        return JsonUtil.treeToValue(rest.getData(), Resource.class);
    }


}
