package com.juemuren.zookeeper.client;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Created by 肖文 on 2018/7/12
 * 订阅子节点状态的变化
 */
public class ChildChangeListener implements IZkChildListener {

    private ZkClient zkClient;

    public ChildChangeListener(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void handleChildChange(String s, List<String> list) throws Exception {
        list.forEach(x -> System.out.println(x));
        System.out.println("连接子节点发生变化：");
    }
}
