package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-15 上午9:40
 * 房间类型 0-自动确认(未付款　已付款已确认) 1-确认后付款(待确认) 2-付款后确认
 */

public enum OrderStatus {
    CANCELED(-1, "已取消"), UNPAID2CONFIRM(0, "未付款未确认"), CONFIRM2PAID(1, "已确认未付款"),
    PAID2CONFIRM(2, "已付款未确认"), PAIDANDCONFIRM(3, "已付款已确认"), COMPLETE(4, "已完成"), REFUNDED(5, "已退款");

    private final int code;
    private String name;

    OrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public static OrderStatus get(int code) {
        for (OrderStatus e : OrderStatus.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
