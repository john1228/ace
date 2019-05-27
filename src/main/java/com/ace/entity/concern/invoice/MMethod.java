package com.ace.entity.concern.invoice;

/**
 * @author john
 * @date 19-5-24 下午6:54
 */
public enum MMethod {
    EMAIL(0, "电子邮件"), EXPRESS(1, "快递");

    private final int code;
    private String name;

    MMethod(int code, String name) {
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

    public static MMethod get(int code) {
        for (MMethod e : MMethod.values()) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
