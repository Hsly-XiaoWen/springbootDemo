package com.tuandai.redis;

/**
 * Created by 肖文 on 2018/4/26
 */
public enum RedisKey {

    TEST_REDIS("TEST_REDIS", "测试使用的key");

    private String key;
    private String dec;
    RedisKey(String key, String des){
        this.key = key;
        this.dec = des;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
