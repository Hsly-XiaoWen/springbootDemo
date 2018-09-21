package com.juemuren.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by 肖文 on 2018/9/5
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    public static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public JsonUtils() {
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static String object2Json(Object o) {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = null;

        try {
            gen = (new JsonFactory()).createGenerator(sw);
            OBJECT_MAPPER.writeValue(gen, o);
        } catch (IOException var11) {
            throw new RuntimeException("不能序列化对象为Json", var11);
        } finally {
            if(null != gen) {
                try {
                    gen.close();
                } catch (IOException var10) {
                    throw new RuntimeException("不能序列化对象为Json", var10);
                }
            }

        }

        return sw.toString();
    }

    public static Map<String, Object> object2Map(Object o) {
        return (Map)OBJECT_MAPPER.convertValue(o, Map.class);
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException var3) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, var3);
        }
    }

    public static <T> List<T> json2List(String json, Class<T> clazz) throws IOException {
        JavaType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
        List<T> list = (List)OBJECT_MAPPER.readValue(json, type);
        return list;
    }

    public static <T> T[] json2Array(String json, Class<T[]> clazz) throws IOException {
        return (T[]) OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> T node2Object(JsonNode jsonNode, Class<T> clazz) {
        try {
            T t = OBJECT_MAPPER.treeToValue(jsonNode, clazz);
            return t;
        } catch (JsonProcessingException var3) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + jsonNode.toString(), var3);
        }
    }

    public static JsonNode object2Node(Object o) {
        try {
            return (JsonNode)(o == null?OBJECT_MAPPER.createObjectNode():(JsonNode)OBJECT_MAPPER.convertValue(o, JsonNode.class));
        } catch (Exception var2) {
            throw new RuntimeException("不能序列化对象为Json", var2);
        }
    }

    public static <T> T json2GenericObject(String json, TypeReference<T> tr) {
        if(json != null && !"".equals(json)) {
            try {
                return OBJECT_MAPPER.readValue(json, tr);
            } catch (Exception var3) {
                throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, var3);
            }
        } else {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json);
        }
    }
}
