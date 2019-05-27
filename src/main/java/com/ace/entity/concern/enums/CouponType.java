package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-15 上午9:32
 */
public enum CouponType {
    Cash(0, "现金券");

    private final int code;
    private String name;

    CouponType(int code, String name) {
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

    public static CouponType get(int code) {
        for (CouponType e : CouponType.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
