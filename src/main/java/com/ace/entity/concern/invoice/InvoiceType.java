package com.ace.entity.concern.invoice;


/**
 * @author john
 * @date 19-5-14 下午6:39
 */
public enum InvoiceType {
    VATI(0, "普通增值税专用发票"), GVATI(1, "增值税专用发票");

    private final int code;
    private String name;

    InvoiceType(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public static InvoiceType get(int code) {
        for (InvoiceType e : InvoiceType.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
