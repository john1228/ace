package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-14 下午6:34
 */
public enum RoomRental {
    HOUR(0, "小时"), PERIOD(1, "整段");
    private final int code;
    private String name;

    RoomRental(int code, String name) {
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

    public static RoomRental get(int code) {
        for (RoomRental e : RoomRental.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
