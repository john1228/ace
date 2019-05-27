package com.ace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
    static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

    public static <E extends Enum<E>> Map<String, String> toCollection(Class<E> enumData) {
        Map<String, String> collection = new LinkedHashMap<>();
        try {
            for (Enum<E> entity : enumData.getEnumConstants()) {
                Method method = enumData.getMethod("getName", null);
                collection.put(entity.name(), (String) method.invoke(entity, null));
            }
        } catch (Exception exp) {
            logger.info("转换筛选出错");
        }

        return collection;
    }

    public static Map<String, String> trueOrFalseCollection(String trueLabel, String falseLabel) {
        Map<String, String> collection = new LinkedHashMap<>();
        collection.put("true", trueLabel);
        collection.put("false", falseLabel);
        return collection;
    }

    public static Map<String, String> toCollection(String... args) {
        Map<String, String> collection = new LinkedHashMap<>();
        for (String str : args) {
            collection.put(str, str);
        }
        return collection;
    }
}
