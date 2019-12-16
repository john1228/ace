package com.ace.controller.api.concerns;

/**
 * App订单列表分类
 * <br/>
 * all - 全部
 * <br/>
 * canceled - 已取消
 * <br/>
 * pending - 待付款
 * <br/>
 * confirming - 等待确认
 * <br/>
 * paid - 已支付
 */
public enum ListStatus {
    ALL(-2), CANCELED(-1), PENDING(0), CONFIRMING(1), PAID(2);
    private int code;

    ListStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
