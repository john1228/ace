package com.ace.entity.coupon.concern;

import com.ace.entity.concern.IEnum;

public enum MemberCouponStatus implements IEnum {
    PENDING(0, "未试用"), USED(1, "已试用");

    private int code;
    private String name;

    MemberCouponStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
