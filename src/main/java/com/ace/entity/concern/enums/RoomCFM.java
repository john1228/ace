package com.ace.entity.concern.enums;

/**
 * @author john
 * @date 19-5-15 上午10:59
 */
public enum RoomCFM {
    AUTO(0, "无需确认"), BEFORE(1, "确认后付款"), AFTER(2, "付款后确认");

    private final int code;
    private String name;

    RoomCFM(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public static RoomCFM get(int code) {
        for (RoomCFM e : RoomCFM.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
