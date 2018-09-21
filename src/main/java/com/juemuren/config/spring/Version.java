package com.juemuren.config.spring;

/**
 * Created by 肖文 on 2018/7/10
 */
public enum Version {
    V_1_1_0("1.1.0"),
    V_1_1_1("1.1.1"),
    V_1_1_2("1.1.2");
    String value;

    Version(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
