package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-15 上午9:31
 */
public enum CouponStatus {
    PENDING(0, "未使用"), USED(1, "已使用");

    private final int code;
    private String name;

    CouponStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }


    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public static CouponStatus get(int code) {
        for (CouponStatus e : CouponStatus.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
