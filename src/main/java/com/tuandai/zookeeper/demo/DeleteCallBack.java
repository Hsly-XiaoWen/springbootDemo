package com.tuandai.zookeeper.demo;

import org.apache.zookeeper.AsyncCallback;

/**
 * Created by 肖文 on 2018/7/12
 */
public class DeleteCallBack implements AsyncCallback.VoidCallback {

    @Override
    public void processResult(int i, String s, Object o) {
        if (i == 0) {
            System.out.println("删除了"+s+"节点的");
        }
        System.out.println(o);
    }
}
