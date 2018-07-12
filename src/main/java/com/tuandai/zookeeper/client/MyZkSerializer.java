package com.tuandai.zookeeper.client;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by 肖文 on 2018/7/12
 */
public class MyZkSerializer implements ZkSerializer {

    @Override
    public byte[] serialize(Object o) throws ZkMarshallingError {
        if (o == null) {
            return null;
        }
        String data = JSON.toJSONString(o);
        return data.getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return JSON.toJSON(new String(bytes));
    }
}
