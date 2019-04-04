package com.ace.util;

import com.ace.entity.concern.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class CollectionUtil {
    static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

    public static <E extends Enum<E>> Map<String, String> toCollection(Class<E> enumData) {
        Map<String, String> collection = new LinkedHashMap<>();
        for (Enum<E> enumVal : enumData.getEnumConstants()) {
            collection.put(enumVal.name(), enumVal.toString());
        }
        return collection;
    }

    public static Map<String, String> trueOrFalseCollection(String trueLabel, String falseLabel) {
        Map<String, String> collection = new LinkedHashMap<>();
        collection.put("true", trueLabel);
        collection.put("false", falseLabel);
        return collection;
    }

    public static void main(String[] args) {
        System.err.println(EnumUtils.Week.FRIDAY.name() + ":" + EnumUtils.Week.FRIDAY.toString());
        System.err.println(EnumUtils.Week.valueOf("FRIDAY").toString());
    }
}
