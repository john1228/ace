package com.ace.entity.concern;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EnumUtils {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Week {
        MONDAY("周一"), TUESDAY("周二"), WEDNESDAY("周三"), THURSDAY("周四"), FRIDAY("周五"), SATURDAY("周六"), SUNDAY("周日");
        private String name;

        Week(String name) {

            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
