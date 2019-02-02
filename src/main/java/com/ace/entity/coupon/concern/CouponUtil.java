package com.ace.entity.coupon.concern;

import com.ace.entity.concern.IEnum;

public class CouponUtil {
    public enum Status implements IEnum {
        PENDING(0, "未试用"), USED(1, "已试用");;

        private int code;
        private String name;

        Status(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum Type implements IEnum {
        Cash(0, "现金券");

        private int code;
        private String name;

        Type(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum Expired implements IEnum {
        Convention(0, "约定时间"), Assignment(1, "发放");
        private int code;
        private String name;

        Expired(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
