package com.ace.entity.concern.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author john
 * @date 19-5-15 上午11:00
 */
public enum RoomPublish {
    PRIVATE(0, "自有"), PUBLIC(1, "公开");
    private final int code;
    private String name;

    RoomPublish(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public static RoomPublish get(int code) {
        for (RoomPublish e : RoomPublish.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
