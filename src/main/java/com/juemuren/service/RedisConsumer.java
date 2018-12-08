package com.juemuren.service;

import com.juemuren.dto.Result;
import com.juemuren.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 肖文 on 2018/11/6.
 */
@Service
public class RedisConsumer {

    public void receiveMessage(String message) {
        //收到通道的消息之后执行秒杀操作(超卖)
//        String[] array = message.split(";");
//        if(redisUtil.getValue(array[0])==null){//control层已经判断了，其实这里不需要再判断了
//            Result result = seckillService.startSeckilDBPCC_TWO(Long.parseLong(array[0]), Long.parseLong(array[1]));
//            if(result.equals(Result.ok(SeckillStatEnum.SUCCESS))){
//                WebSocketServer.sendInfo(array[0].toString(), "秒杀成功");//推送给前台
//            }else{
//                WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
//                redisUtil.cacheValue(array[0], "ok");//秒杀结束
//            }
//        }else{
//            WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
//        }
    }

}
