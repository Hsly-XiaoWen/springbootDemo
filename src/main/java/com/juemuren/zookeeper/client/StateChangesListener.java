package com.juemuren.zookeeper.client;

import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

/**
 * Created by 肖文 on 2018/7/12
 * 订阅节点连接及状态的变化情况
 */
public class StateChangesListener implements IZkStateListener {

    private ZkClient zkClient;

    public StateChangesListener(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
        System.out.println("节点连接及状态变化："+keeperState.name());
    }

    @Override
    public void handleNewSession() throws Exception {
        System.out.println("节点Session变化。。。");
    }

    @Override
    public void handleSessionEstablishmentError(Throwable throwable) throws Exception {

    }
}
