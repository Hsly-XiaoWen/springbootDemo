package com.tuandai.zookeeper.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by 肖文 on 2018/7/12
 */
public class ReadWatch implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("该监听事件被调用了==========="+watchedEvent.getType());
        switch (watchedEvent.getType()) {
            case NodeCreated:
                System.out.println("创建了新的节点");
                break;
            case NodeDeleted:
                System.out.println("删除了"+watchedEvent.getPath()+"节点");
                break;
            case NodeDataChanged:
                System.out.println("更新了"+watchedEvent.getPath()+"信息");
                break;
            case NodeChildrenChanged:
                System.out.println("子节点发生了变化");
                break;
        }
    }
}
