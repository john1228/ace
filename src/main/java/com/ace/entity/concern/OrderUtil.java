package com.ace.entity.concern;

public class OrderUtil {
    public enum Status implements IEnum {
        CANCELED(-1, "已取消"), PENDING(0, "待付款"), PAID(1, "已付款"), REFUNDED(2, "已退款");

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

}
