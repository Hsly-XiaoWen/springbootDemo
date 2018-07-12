package com.tuandai.zookeeper.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by 肖文 on 2018/5/3
 * 使用原生的zookeeper api 操作zk
 * Watch没有监听的创建、删除节点的方法，使用异步回调完成后续业务逻辑
 */
public class ZookeeperDemo {

    private ZooKeeper zooKeeper;
    private Watcher watcher;

    @Before
    public void init() {
        //监听等待zk通知
//        watcher = (event) -> {
//            String path=event.getPath();
//            System.out.println(path+"已经触发了watch机制========= " + event.getState());};
        watcher = new ReadWatch();
        try {
            zooKeeper = new ZooKeeper("118.89.29.12:2181", 3000, watcher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取zk节点信息
     * @throws InterruptedException
     */
    @Test
    public void getFromZk() throws InterruptedException {
        String value=null;
        String value1=null;
        try {
            byte[] data = zooKeeper.getData("/test/love", true, null);
            Stat stat = new Stat();
            //使用Stat参数在方法执行完成之后会对stat参数重新赋值
            byte[] data1 = zooKeeper.getData("/test/love", watcher, stat);
            System.out.println("返回对象是："+stat.getVersion());
            value = new String(data);
            value1 = new String(data1);
        } catch (Exception e) {

        }
        System.out.println("测试获取的值是"+value);
        System.out.println("测试获取的值是"+value1);
        while (true) {
            Thread.sleep(10000);
        }
    }

    /**
     * 更新节点信息
     */
    @Test
    public void update() throws KeeperException, InterruptedException {
        String path = "/test/love";
        Stat stat = this.zooKeeper.setData(path, "sivan".getBytes(), -1);
        System.out.println(stat.getVersion());
    }

    /**
     * 获取指定节点的所有子节点
     * @throws Exception
     */
    @Test
    public void getChildren() throws Exception{
        List<String> result = zooKeeper.getChildren("/test", true);
        result.forEach(x -> {
            System.out.println(x);
        });
        while (true) {
            Thread.sleep(10000);
        }
    }

    /**
     * 创建节点
     * 1 PERSISTENT：持久；2 PERSISTENT_SEQUENTIAL：持久顺序；3 EPHEMERAL：临时；4 EPHEMERAL_SEQUENTIAL：临时顺序。
     *
     */
    @Test
    public void create() throws InterruptedException {
        String path = "/test/address";
        try {
            //执行完成后会执行CreateCallback回调方法
            this.zooKeeper.create(path, "China".getBytes()
                    , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new CreateCallback(),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(10000);
    }


    @Test
    public void delete() {
        String path = "/test/address";
        try {
            this.zooKeeper.delete(path,-1,new DeleteCallBack(),"删除了节点");
//            this.zooKeeper.delete("/test/address",-1);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
