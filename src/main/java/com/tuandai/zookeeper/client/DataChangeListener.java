package com.tuandai.zookeeper.client;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by 肖文 on 2018/7/12
 * 订阅节点数据的变化
 */
public class DataChangeListener implements IZkDataListener {

    private ZkClient zkClient;

    public DataChangeListener(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void handleDataChange(String s, Object o) throws Exception {
        System.out.println(s + "更新了数据"+s+"==="+o.toString());
    }

    @Override
    public void handleDataDeleted(String s) throws Exception {
        System.out.println(s + "age 节点被删除了"+s);
        zkClient.unsubscribeDataChanges(s + "age",this);
    }
}
