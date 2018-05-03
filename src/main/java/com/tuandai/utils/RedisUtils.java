package com.tuandai.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tuandai.redis.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by 肖文 on 2018/4/26
 */
public class RedisUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    /**
     * Redis放置数据 设置超时时间
     * @param redisTemplate
     * @param data 数据
     * @param key HashMap key
     * @param childKey value key
     * @param expire 超时时间
     * @param <T>
     */
    public static <T> void putForHashs(RedisTemplate<String,String> redisTemplate, T data, String key, String childKey, Expire expire){
        HashOperations<String, String, String> operations = redisTemplate.opsForHash();
        String jsonData;
        try {
            jsonData = JSON.toJSONString(data, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
            operations.put(key, childKey, jsonData);
            setTimeOut(redisTemplate,key,expire);
        } catch (Exception e) {
            logger.info("putForHash放置数据异常{}",e.getMessage());
        }
    }

    /**
     * BoundHashOperations使用的是boundHashOps添加数据
     * @param redisTemplate
     * @param data
     * @param key
     * @param childKey
     * @param expire
     * @param <T>
     */
    public static <T> void putForHash(RedisTemplate<String,String> redisTemplate, T data, String key, String childKey, Expire expire){
        BoundHashOperations<String, String, String> boundHashOps;
        String jsonData;
        try {
            boundHashOps = redisTemplate.boundHashOps(key);
            jsonData = JSON.toJSONString(data, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
            boundHashOps.put(childKey, jsonData);
            setTimeout(boundHashOps, expire);
        } catch (Exception e) {
            logger.error("putForHash{},{},{}", key, childKey, e);
        }
    }

    public static <T> void putForHash(RedisTemplate<String,String> redisTemplate, T data, String key, String childKey){
        putForHash(redisTemplate,data,key,childKey,null);
    }

    public static <T> void putForHash(RedisTemplate<String,String> redisTemplate, T data, RedisKey key, String childKey){
        putForHash(redisTemplate,data,key.getKey(),childKey,null);
    }

    public static <T> void putForHash(RedisTemplate<String,String> redisTemplate, T data, RedisKey key, String childKey,Expire expire){
        putForHash(redisTemplate,data,key.getKey(),childKey,expire);
    }

    /**
     * 批量添加Hash
     * 只需要添加父key,子key对应map的key
     */
    public static void putForAllHash(RedisTemplate<String,String> redisTemplate, Map<String,String> maps, String key, Expire expire){
        BoundHashOperations<String, String, String> boundHashOps;
        try {
            boundHashOps = redisTemplate.boundHashOps(key);
            boundHashOps.putAll(maps);
            setTimeout(boundHashOps, expire);
        } catch (Exception e) {
            logger.error("putForHash{},{},{}", key, e);
        }
    }

    /**
     * 放置key-value类型 使用BoundValueOperations
     * @param redisTemplate
     * @param data
     * @param key
     * @param expire
     * @param <T>
     */
    public static<T> void putForValue(RedisTemplate<String,String> redisTemplate, T data, String key, Expire expire) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String jsonData;
        try {
            jsonData = JSON.toJSONString(data, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty
                    , SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
            operations.set(key,jsonData);
            setTimeOut(redisTemplate,key,expire);
        } catch (Exception e) {
            logger.error("putForValue添加失败{}",e.getMessage());
        }
    }

    /**
     * 有主key的添加,到redis key为主key+childKey
     * @param redisTemplate
     * @param data
     * @param key
     * @param childKey
     * @param expire
     * @param <T>
     */
    public static <T> void putForValue(RedisTemplate<String, String> redisTemplate, T data, String key, String childKey, Expire expire) {
        BoundValueOperations<String, String> boundValueOps;
        String keyName = key;
        String jsonData;
        try {
            if (childKey != null) {
                keyName = key.concat(childKey);
            }
            jsonData = JSON.toJSONString(data, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
            boundValueOps = redisTemplate.boundValueOps(keyName);
            boundValueOps.set(jsonData);
            setTimeout(boundValueOps, expire);
        } catch (Exception e) {
            logger.error("putForValue{}", key, childKey, e);
        }

    }
    /**
     * redis添加list
     */
    public static<T> void putForList(RedisTemplate<String,String> redisTemplate, List<T> data, String key, Expire expire) {
        BoundListOperations<String, String> ops;
        String jsonData;
        try {
            ops = redisTemplate.boundListOps(key);
            jsonData = JSON.toJSONString(data, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
            ops.leftPush(jsonData);
            setTimeOut(redisTemplate, key, expire);
        } catch (Exception e) {
            logger.error("putFotList放置数据异常{}",e.getMessage());
        }
    }

    /**
     * 设置超时时间
     * @param boundHashOps
     * @param expire
     */
    private static void setTimeout(BoundKeyOperations boundHashOps, Expire expire) {
        if (expire != null) {
            if (expire.expireDate != null) {
                boundHashOps.expireAt(expire.expireDate);
            } else {
                boundHashOps.expire(expire.timeout, expire.unit);
            }
        }
    }

    /**
     * 获取hash value
     * @param redisTemplate
     * @param key
     * @param childKey
     * @param type
     * @param <T>
     * @return
     */
    public static<T> T getForHash(RedisTemplate<String, String> redisTemplate, String key, String childKey, Type type) {
        BoundHashOperations<String, String, String> boundHashOps;
        String jsonData;
        try {
            boundHashOps = redisTemplate.boundHashOps(key);
            jsonData = boundHashOps.get(childKey);
//            if (type.equals(String.class)) {
//                return (T)jsonData;
//            }
            return JSONObject.parseObject(jsonData,type);
        } catch (Exception e) {
            logger.error("getForHash{}", key, childKey, e);
        }
        return null;
    }

    public static <T> T getForHash(RedisTemplate<String, String> redisTemplate, String key, String childKey, Class<T> tClass) {
        return getForHash(redisTemplate, key, childKey, (Type) tClass);
    }

    public static <T> List<T> getForHashList(RedisTemplate<String, String> redisTemplate, String key, String childKey, Type type) {
        return null;
    }
    /**
     * LocalTime转化为时间戳设置超时时间
     * @param localDateTime
     * @param unit
     * @return
     */
    public static Expire expire(LocalDateTime localDateTime,TimeUnit unit){
        if (localDateTime == null) {
            return null;
        }
        long time = Timestamp.valueOf(localDateTime).getTime();
        return new Expire(time, unit);
    }

    /**
     *localDateTime转化为Date
     * @param localDateTime
     * @return
     */
    public static Expire expire(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return new Expire(LocalDateTimeUtils.getDate(localDateTime));
    }


    /**
     * 设置超时时间
     * @param redisTemplate
     * @param key
     * @param expire
     */
    private static void setTimeOut(RedisTemplate<String,String> redisTemplate,String key,Expire expire){
        if (expire != null) {
            logger.info("时间是{}",expire.expireDate);
            if (expire.expireDate != null) {
                redisTemplate.expireAt(key, expire.expireDate);
            } else {
                redisTemplate.expire(key, expire.timeout, expire.unit);
            }
        }
    }

    /**
     * 超时参数
     * 两种设置超时时间
     * 时间戳：long expire()
     * 时间点：date expireAt()
     */
    private static class Expire {
        private Date expireDate;
        private long timeout;
        private TimeUnit unit;

        Expire(Date expireDate) {
            this.expireDate = expireDate;
        }

        Expire(long timeout, TimeUnit unit) {
            this.timeout = timeout;
            this.unit = unit;
        }

    }
}
