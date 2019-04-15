package com.ace.entity.concern;

public class InvoiceUtil {
    public enum Type implements IEnum {
        GVATI(0, "普通增值税专用发票"), VATI(0, "增值税专用发票"), EI(2, "电子发票");

        private int code;
        private String name;

        Type(int code, String name) {
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
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }
    }
}
