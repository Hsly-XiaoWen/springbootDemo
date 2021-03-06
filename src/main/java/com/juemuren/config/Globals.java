package com.juemuren.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by 肖文 on 2018/7/12
 */
public class Globals {

    @Value("${zookeeper.server.host}")
    private String zkHost;
    @Value("${zookeeper.server.sessionTimeout}")
    private int sessionTimeout;
    @Value("${zookeeper.server.connectionTimeout}")
    private int connectionTimeout;
    @Value("${test.name:www.bai.com}")
    private String management;

    public String getZkHost() {
        return zkHost;
    }

    public void setZkHost(String zkHost) {
        this.zkHost = zkHost;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }
}
