package com.stu.otseaclient.general;

import org.json.JSONObject;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/7 19:50
 * @Description:
 */
public interface IRestResponse {
    void onRestResponse(Rest<String> rest);
}
