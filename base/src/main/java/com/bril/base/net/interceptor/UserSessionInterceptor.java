package com.bril.base.net.interceptor;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 统一用户登录信息
 */

public class UserSessionInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //Account account = LoginHelper.getInstance().getCurrentAccount();
        HttpUrl.Builder httpBulder = request.url().newBuilder();
       // if (account != null) {
            //httpBulder.addQueryParameter("openSessionId", account.getOpenSessionId());
        //}
        Request build = request.newBuilder().url(httpBulder.build()).build();
        return chain.proceed(build);
    }
}
