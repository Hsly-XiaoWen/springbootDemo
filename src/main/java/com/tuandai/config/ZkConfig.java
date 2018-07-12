package com.tuandai.config;

import com.tuandai.zookeeper.client.MyZkSerializer;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 肖文 on 2018/7/12
 */
@Configuration
public class ZkConfig {

    @Autowired
    private Globals globals;

    @Bean
    public ZkClient getZkClient() {
        ZkClient zkClient = new ZkClient(this.globals.getZkHost(), this.globals.getSessionTimeout()
                , this.globals.getConnectionTimeout());
        zkClient.setZkSerializer(new MyZkSerializer());
        return zkClient;
    }
}
