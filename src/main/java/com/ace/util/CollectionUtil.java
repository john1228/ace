package com.ace.util;

import java.util.HashMap;
import java.util.Map;

public class CollectionUtil {
    public static <E extends Enum<E>> Map<String, String> toCollection(Class<E> enumData) {
        Map<String, String> collection = new HashMap<>();
        for (Enum<E> enumVal : enumData.getEnumConstants()) {
            collection.put(enumVal.name(), enumVal.toString());
        }
        return collection;
    }
}
