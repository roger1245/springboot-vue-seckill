package com.hhj.seckill.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class JsonUtil {
    private static ObjectMapper objMapper = new ObjectMapper();

    
    public static <T> T toObj(String jsonString, Class<T> clazz) {
        objMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return objMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("Json字符串转换为对象出错", e);
        }
        return null;
    }

    
    public static String toJson(Object obj) {
        if (obj instanceof Integer || obj instanceof Long || obj instanceof Float ||
                obj instanceof Double || obj instanceof Boolean || obj instanceof String) {
            return String.valueOf(obj);
        }
        try {
            return objMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("对象转换为Json字符串出错", e);
        }
        return null;
    }

    
    public static <T, E> T toObjArray(String jsonString, Class<T> collectionClass, Class<E>... elementClasses) {
        try {
            JavaType javaType = objMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
            return objMapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            log.error("json字符串转化为集合出错", e);
        }
        return null;
    }
}
