package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-15 上午9:40
 */
public enum OrderStatus {
    CANCELED(-1, "已取消"), UNPAID(0, "待付款"), UNPAID2CONFIRM(1, "未付款待确认"),
    PAID2CONFIRM(2, "已付款待确认"), PAIDANACONFIRM(3, "已付款已确认"), REFUNDED(4, "已退款");

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
