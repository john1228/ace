package com.ace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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
}
