package com.juemuren.utils;

import java.io.*;

/**
 * Created by 肖文 on 2018/7/7
 */
public class Byte2ObjectUtils {

    /**
     * 对象转化为字节数组
     * @param object
     * @return
     */
    public static byte[] object2Byte(Object object) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(bo);
            outputStream.writeObject(object);

            bytes = bo.toByteArray();
            bo.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 字节数组转化为对象
     * @param bytes
     * @return
     */
    public static Object byte2Object(byte[] bytes) {
        Object object = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            object=objectInputStream.readObject();
            byteArrayInputStream.close();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
