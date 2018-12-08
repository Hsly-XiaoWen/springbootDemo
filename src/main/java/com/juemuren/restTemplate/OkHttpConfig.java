package com.juemuren.restTemplate;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by 肖文 on 2018/12/8.
 */
public class OkHttpConfig {

    @Value("${app.okhttp.read.timeout:10000}")
    private long okHttpReadTimeout;
    @Value("${app.okhttp.connect.timeout:10000}")
    private long okHttpConnectTimeout;
    @Value("${app.okhttp.write.timeout:10000}")
    private long okHttpWriteTimeout;
    /**
     * 最大空闲连接数
     */
    @Value("${app.okhttp.max.idle:5}")
    private int okHttpMaxIdle;
    /**
     * 连接存活时间, 单位: 秒
     */
    @Value("${app.okhttp.alive.duration:300}")
    private int okHttpAliveDuration;

    public long getOkHttpReadTimeout() {
        return okHttpReadTimeout;
    }

    public void setOkHttpReadTimeout(long okHttpReadTimeout) {
        this.okHttpReadTimeout = okHttpReadTimeout;
    }

    public long getOkHttpConnectTimeout() {
        return okHttpConnectTimeout;
    }

    public void setOkHttpConnectTimeout(long okHttpConnectTimeout) {
        this.okHttpConnectTimeout = okHttpConnectTimeout;
    }

    public long getOkHttpWriteTimeout() {
        return okHttpWriteTimeout;
    }

    public void setOkHttpWriteTimeout(long okHttpWriteTimeout) {
        this.okHttpWriteTimeout = okHttpWriteTimeout;
    }

    public int getOkHttpMaxIdle() {
        return okHttpMaxIdle;
    }

    public void setOkHttpMaxIdle(int okHttpMaxIdle) {
        this.okHttpMaxIdle = okHttpMaxIdle;
    }

    public int getOkHttpAliveDuration() {
        return okHttpAliveDuration;
    }

    public void setOkHttpAliveDuration(int okHttpAliveDuration) {
        this.okHttpAliveDuration = okHttpAliveDuration;
    }
}
