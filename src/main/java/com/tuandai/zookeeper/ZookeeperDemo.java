package com.tuandai.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

/**
 * Created by 肖文 on 2018/5/3
 */
public class ZookeeperDemo {

    @Test
    public void test() {
        Watcher watcher = (event) -> {
            System.out.println("receive message " + event);
        };
        String value=null;
        try {
            ZooKeeper zookeeper = new ZooKeeper("118.89.29.12:2178", 3000, watcher);
            byte[] data = zookeeper.getData("/node_1", watcher, null);
            value = new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("测试获取的值是"+value);
    }
}
