package com.stu.otseaclient.general;

import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stu.otseaclient.enumreation.RestCode;
import com.stu.otseaclient.enumreation.TagEnum;
import com.stu.otseaclient.util.JsonUtil;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/7 19:05
 * @Description:
 */
@SuppressWarnings("all")
public class HttpRequest {
    //连接超时时间（秒）
    public static final int CONNECT_TIMEOUT = 5;
    private final static HttpRequest instance = new HttpRequest();
    private String token = null;

    public static HttpRequest getInstance() {
        return instance;
    }

    /**
     * 异步发送get请求，返回stream结果数据
     *
     * @param url
     * @param streamResponse
     */
    public void asyncGetStream(String url, IStreamResponse streamResponse) {
        Async.run(() -> {
            InputStream inputStream = null;
            try {
                Request request = buildRequest().url(url).get().build();
                OkHttpClient client = buildClient();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    inputStream = response.body().byteStream();
                    streamResponse.onStreamResponse(inputStream);
                } else {
                    Log.e(TagEnum.REQUEST_NET, "fail to get syncGetStream response : " + url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 异步发送post请求,返回rest格式数据
     *
     * @param url
     * @param body
     * @param restResponse
     */
    public void asyncPostRest(String url, RequestBody body, IRestResponse restResponse) {
        OkHttpClient client = buildClient();
        Request request = buildRequest().url(url).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TagEnum.REQUEST_NET, "fail to connect to server " + url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (restResponse == null) return;
                try {
                    ObjectNode restNode = (ObjectNode) JsonUtil.getObjectMapper().readTree(response.body().string());

                    Rest rest = packFromJson(restNode);

                    restResponse.onRestResponse(rest);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TagEnum.REQUEST_NET, "there is a exception on running callback " + url);
                }
            }
        });
    }

    /**
     * 同步发送post请求
     *
     * @param url
     * @param body
     * @return
     */
    public Rest syncPost(String url, RequestBody body) {
        OkHttpClient client = buildClient();
        Request request = buildRequest().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new RuntimeException("fail to get server result!!");

            String responseStr = response.body().string();

            ObjectNode restNode = (ObjectNode) JsonUtil.getObjectMapper().readTree(responseStr);
            return packFromJson(restNode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置请求token
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    private OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
    }

    private Request.Builder buildRequest() {
        Request.Builder builder = new Request.Builder();
        if (token != null) builder.addHeader("token", token);
        return builder;
    }

    private Rest packFromJson(ObjectNode json) {
        int code = 0;

        code = json.get("code").asInt();

        if (code != RestCode.SUCCEED)
            Log.w(TagEnum.REQUEST_NET, "there is a unexpected and is body:" + json);

        String msg = json.get("msg").asText();
        JsonNode data = json.get("data");

        return new Rest(code, msg, data);
    }
}


