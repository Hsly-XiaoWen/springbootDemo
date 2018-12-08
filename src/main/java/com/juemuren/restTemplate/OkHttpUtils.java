package com.juemuren.restTemplate;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by 肖文 on 2018/12/8.
 */
public class OkHttpUtils {
    public static OkHttpClient.Builder okHttpClientBuilder(OkHttpConfig applicationConstant) {
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(applicationConstant.getOkHttpReadTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(applicationConstant.getOkHttpConnectTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(applicationConstant.getOkHttpWriteTimeout(), TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(applicationConstant.getOkHttpMaxIdle(),
                        applicationConstant.getOkHttpAliveDuration(), TimeUnit.SECONDS));
    }
}
