package com.bril.base.net;


import com.bril.base.net.down.FileConverterFactory;
import com.bril.base.net.down.body.HttpClientHelper;
import com.bril.base.net.down.body.ProgressRequestListener;
import com.bril.base.net.down.body.ProgressResponseListener;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by 123456 on 2017/8/9.
 */

public class DownServiceGenerator {
    private static final String HOST = ApiService.Base_URL;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(FileConverterFactory.create());

    public static <T> T createService(Class<T> tClass) {
        return builder.build().create(tClass);
    }


    /**
     * 创建带响应进度(下载进度)回调的service
     */
    public static <T> T createResponseService(Class<T> tClass, ProgressResponseListener listener) {
        OkHttpClient client = HttpClientHelper.addProgressResponseListener(new OkHttpClient.Builder(), listener).build();
        return builder
                .client(client)
                .build()
                .create(tClass);
    }


    /**
     * 创建带请求体进度(上传进度)回调的service
     */
    public static <T> T createReqeustService(Class<T> tClass, ProgressRequestListener listener) {
        OkHttpClient client = HttpClientHelper.addProgressRequestListener(new OkHttpClient.Builder(), listener).build();
        return builder
                .client(client)
                .build()
                .create(tClass);
    }
}
