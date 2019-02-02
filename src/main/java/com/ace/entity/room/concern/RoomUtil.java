package com.ace.entity.room.concern;

import com.ace.entity.concern.IEnum;

public class RoomUtil {
    public enum Type implements IEnum {
        Clazz1(0, "类型1"), Clazz2(1, "类型２");

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

    public enum Layout implements IEnum {
        ROUND(0, "圆形"), SQUARE(1, "方形");

        private int code;
        private String name;

        Layout(int code, String name) {
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
