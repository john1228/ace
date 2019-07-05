package com.ace.controller.api.concerns;

/**
 * @author john
 * @date 19-7-3 下午3:27
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
