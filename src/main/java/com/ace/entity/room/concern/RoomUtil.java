package com.ace.entity.room.concern;

import com.ace.entity.concern.IEnum;


public class RoomUtil {
    public enum Rental implements IEnum {
        HOUR(0, "小时"), PERIOD(1, "整段");
        private int code;
        private String name;

        Rental(int code, String name) {
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
        INDOOR(0, "室内"), OUTDOOR(1, "室外");

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

    public enum Publish implements IEnum {
        PRIVATE(0, "自有"), PUBLIC(1, "公开");

        private int code;
        private String name;

        Publish(int code, String name) {
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

    public enum Charge implements IEnum {
        FREE(0, "免费"), TOLL(1, "收费");

        private int code;
        private String name;

        Charge(int code, String name) {
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

    public enum CFM implements IEnum {
        AUTO(0, "无需确认"), BEFORE(1, "确认后付款"), AFTER(2, "付款后确认");

        private int code;
        private String name;

        CFM(int code, String name) {
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

    public static void main(String[] args) {
        System.err.println(Enum.valueOf(RoomUtil.CFM.class, "AUTO"));
    }
}
