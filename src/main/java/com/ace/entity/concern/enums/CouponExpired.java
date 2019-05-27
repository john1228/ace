package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-15 上午9:36
 */
public enum CouponExpired {
    CONVENTION(0, "约定时间"), ASSIGNMENT(1, "发放");
    private final int code;
    private String name;

    CouponExpired(int code, String name) {
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

    public static CouponExpired get(int code) {
        for (CouponExpired e : CouponExpired.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
