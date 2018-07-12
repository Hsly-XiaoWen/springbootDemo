package com.tuandai.zookeeper.client;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by 肖文 on 2018/7/12
 * 使用zkClient开源项目实现对zk的操作
 */
public class ZkClientDemo {

    private ZkClient zkClient;
    private String groupPath = "/client/";

    @Before
    public void init() {
        this.zkClient = new ZkClient("118.89.29.12:2181", 5000, 5000);
        this.zkClient.setZkSerializer(new MyZkSerializer());

        //订阅节点数据的变化
        this.zkClient.subscribeDataChanges(groupPath + "age",new DataChangeListener(this.zkClient));

        //订阅子节点状态的变化
        this.zkClient.subscribeChildChanges("/client", new ChildChangeListener(this.zkClient));

        //订阅节点连接及状态的变化情况
        this.zkClient.subscribeStateChanges(new StateChangesListener(this.zkClient));
    }

    /**
     * 创建节点
     * @throws InterruptedException
     */
    @Test
    public void create() throws InterruptedException {
        this.zkClient.create(groupPath + "name","xiaowen",CreateMode.EPHEMERAL);
        this.zkClient.createPersistent(groupPath + "age","11");
        this.zkClient.createEphemeral(groupPath + "school","jxlgdx");
        Thread.sleep(100000);
    }

    /**
     * 删除节点
     * @throws InterruptedException
     */
    @Test
    public void delete() throws InterruptedException {
        this.zkClient.delete(groupPath + "age");
        Thread.sleep(100000);
    }

    @Test
    public void getChildren() throws InterruptedException {
        List<String> list = this.zkClient.getChildren("/test");
        list.forEach(x->System.out.println(x));
        this.zkClient.createPersistent("/test/work","tuandai");
        Thread.sleep(1000000);
    }

    @Test
    public void write() throws InterruptedException {
        this.zkClient.writeData(groupPath + "age","小文");
        Thread.sleep(100000);
    }
}
