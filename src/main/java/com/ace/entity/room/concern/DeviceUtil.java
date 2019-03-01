package com.ace.entity.room.concern;

import com.ace.entity.concern.IEnum;

public class DeviceUtil {
    public enum Status implements IEnum {
        ENABLE(0, "已启用"), DISABLE(1, "已停用");

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
