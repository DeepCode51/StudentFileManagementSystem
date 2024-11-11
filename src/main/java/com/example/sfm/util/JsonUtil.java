package com.example.sfm.util;

import com.example.sfm.exception.DangerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Json工具类
 */
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 创建对象节点。
     */
    public static ObjectNode createObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    /**
     * 创建数组节点。
     */
    public static ArrayNode createArrayNode() {
        return OBJECT_MAPPER.createArrayNode();
    }

    /**
     * 将Map转换成Json字符串。
     */
    public static String convertMapToJson(Map<?, ?> map) {
        try {
            return OBJECT_MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            LOGGER.error("Map转换Json字符串失败。", e);
            throw new DangerException("Map转换Json字符串失败");
        }
    }

    /**
     * 将Json字符串转化为Map。
     */
    public static Map<String, Object> convertJsonToMap(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            LOGGER.error("解析Json失败。", e);
            throw new DangerException("解析Json失败");
        }
    }

}
