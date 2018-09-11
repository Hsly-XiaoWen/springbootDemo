package com.tuandai.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 肖文 on 2018/7/14
 * curator实现了三种缓存：NodeCache、PathChildrenCache、TreeCache
 * NodeCache是用来监听数据节点的变化的
 * PathChildrenCache是用来监听指定节点的子节点变化的
 * TreeCache结合上面两种缓存实现的，既可以实现指定节点的数据变化、又可以实现指定节点下的子节点的变化情况
 * 使用curator框架操作zk
 */
public class CuratorDemo {

    private CuratorFramework client;
    /**
     * 创建zk连接
     */
    @Before
    public void init() throws Exception {
        //设置重试机制，刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        RetryPolicy retryPolicy =new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("112.74.161.161:2181")//
                .sessionTimeoutMs(5000)//会话超时时间
                .connectionTimeoutMs(15000)//连接超时时间
                .retryPolicy(retryPolicy)
                .build();
        this.client.start();

        TreeCache cache = new TreeCache(this.client,"/curator");
        //添加节点变化监听器
        cache.getListenable().addListener((clients,event)->{
            ChildData data = event.getData();
            if (data != null) {
                switch (event.getType()) {
                    case NODE_ADDED:
                        System.out.println(data.getPath()+"添加了节点"+data.getData());
                        break;
                    case NODE_REMOVED:
                        System.out.println(data.getPath()+"删除了节点"+data.getData());
                        break;
                    case NODE_UPDATED:
                        System.out.println(data.getPath()+"更新了节点"+data.getData());
                        break;
                    default:
                        break;
                }
            }
        });
        cache.start();
    }

    /**
     * 创建新节点
     */
    @Test
    public void create() throws Exception {
        //若创建节点的父节点不存在会先创建父节点再创建子节点
        String path = this.client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curator/name");
        System.out.println(path+"---------------");
        Thread.sleep(10000);
    }


    /**
     * 获取数据
     */
    @Test
    public void getData() throws Exception {
        //单纯返回节点数据
        byte[] data = this.client.getData().forPath("/curator/name");
        System.out.println(new String(data));

        Stat stat = new Stat();
        //在获取节点内容的同时把状态信息存入Stat对象
        data=this.client.getData().storingStatIn(stat).forPath("/curator/name");
        System.out.println(new String(data)+"======"+stat.getVersion());
    }

    /**
     * 修改数据
     */
    @Test
    public void setData() throws Exception {
        //修改节点数据返回节点状态信息
        Stat stat = new Stat();
        String re = new String(client.getData().storingStatIn(stat)//在获取节点内容的同时把状态信息存入Stat对象
                .forPath("/curator/name"));
        System.out.println(re);
        //修改节点数据,在执行下代码之前其他线程执行了修改会导致stat 版本修改而抛出版本异常
        Stat data1 = this.client.setData().withVersion(stat.getVersion()).forPath("/curator/name", "123".getBytes());
        System.out.println(stat.toString());
        System.out.println(data1.toString());
        Thread.sleep(10000);
    }

    /**
     * 删除节点
     */
    @Test
    public void delete() throws Exception {
        //若节点下还包含字节点直接删除会报错，不存在指定节点时会报错
//        this.client.delete().forPath("/curator/test");

        //添加deletingChildrenIfNeeded表示删除时级联删除子节点。
//        this.client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator/test");

        /**
         * guaranteed()保障机制，若未删除成功，只要会话有效会在后台一直尝试删除；当指定版本非最后版本会报错但是一样可以正常删除
         */
        this.client.delete().guaranteed().withVersion(-1).forPath("/curator/name");
        Thread.sleep(10000);
    }

    /**
     * 异步
     * @throws Exception
     */
    @Test
    public void sync() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(3);//线程池
        this.client.getData().inBackground((curator, event) -> {
            System.out.println(event.getType() + "=========");
            int re = event.getResultCode();//执行成功为0
            System.out.println(re);

            String path = event.getPath();
            System.out.println(path);
            Stat stat = event.getStat();
            System.out.println(stat);
        }, es).forPath("/curator/name");
        Thread.sleep(10000);
    }
}
