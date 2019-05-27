package com.ace.entity.concern.invoice;

/**
 * @author john
 * @date 19-5-14 下午6:39
 */
public enum InvoiceStatus {
    PENDING(0, "未开"), APPLYING(1, "待开"), INVOICED(2, "已开"), SHIPPED(3, "已邮寄");
    private int code;
    private String name;

    InvoiceStatus(int code, String name) {
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

    public static InvoiceStatus get(int code) {
        for (InvoiceStatus e : InvoiceStatus.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
