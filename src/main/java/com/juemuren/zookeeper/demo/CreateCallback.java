package com.juemuren.zookeeper.demo;

import org.apache.zookeeper.AsyncCallback.StringCallback;

/**
 * Created by 肖文 on 2018/7/12
 */
public class CreateCallback implements StringCallback {


    /**
     *
     * @param rc 为服务器的响应码，0表示调用成功，-4表示连接已断开，-110表示指定节点已存在，-112表示会话已过期
     * @param path 调用create方法时传入的path
     * @param ctx 调用create方法时传入的ctx
     * @param name 创建成功的节点名称
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println(rc+"=========");
        if (rc == 0) {
            System.out.println(path+"调用创建节点成功"+name);
        }
        System.out.println("创建失败"+path+"请求参数是："+ctx);
    }
}
