package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-7-6 下午6:48
 */
public enum RefundStatus {
    PENDING("申请中"), AGREED("已同意"), REJECTED("已拒绝"), COMPLETED("已完成");
    private String name;

    RefundStatus(String name) {
        this.name = name;
    }
}
