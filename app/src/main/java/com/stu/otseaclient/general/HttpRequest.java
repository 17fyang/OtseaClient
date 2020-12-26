package com.stu.otseaclient.general;

import android.util.Log;
import com.stu.otseaclient.enumreation.RestCode;
import com.stu.otseaclient.enumreation.TagEnum;
import okhttp3.*;
import org.json.JSONObject;

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
    public static final int CONNECT_TIMEOUT = 5;
    private final static HttpRequest instance = new HttpRequest();

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
                Request request = new Request.Builder().url(url).get().build();
                OkHttpClient client = new OkHttpClient.Builder().readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    inputStream = response.body().byteStream();
                    streamResponse.onStreamResponse(inputStream);
                } else {
                    Log.e(TagEnum.REQUEST_NET, "fail to get syncGetStream response");
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
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).post(body).build();
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
                    JSONObject json = new JSONObject(response.body().string());
                    int code = json.getInt("code");
                    if (code != RestCode.SUCCEED)
                        Log.w(TagEnum.REQUEST_NET, "there is a unexpected and is body:" + json);

                    String msg = json.getString("msg");
                    String data = json.getString("data");

                    restResponse.onRestResponse(new Rest(code, msg, data));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TagEnum.REQUEST_NET, "there is a exception on running callback " + url);
                }
            }
        });
    }
}


