package com.ace.entity.concern.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author john
 * @date 19-5-14 下午6:19
 */
public enum Week {
    MONDAY("周一"), TUESDAY("周二"), WEDNESDAY("周三"), THURSDAY("周四"), FRIDAY("周五"), SATURDAY("周六"), SUNDAY("周日");
    private String name;

    Week(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Map<String, String> toOptions() {
        Map<String, String> collection = new LinkedHashMap<>();
        for (Week week : Week.values()) {
            collection.put(week.name(), week.toString());
        }
        return collection;
    }
}
