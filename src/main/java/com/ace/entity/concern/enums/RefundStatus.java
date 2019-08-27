package com.ace.entity.concern.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author john
 * @date 19-7-6 下午6:48
 */
public enum RefundStatus {
    REJECTED(-1, "已拒绝"), PENDING(0, "申请中"), AGREED(1, "已同意"), COMPLETED(2, "已完成");
    private final int code;
    private String name;

    RefundStatus(int code, String name) {
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

    public static RefundStatus get(int code) {
        for (RefundStatus e : RefundStatus.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
