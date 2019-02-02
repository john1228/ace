package com.ace.util;

public enum AttributeType {
    choose("选择"), input("输入");
    private String name;

    AttributeType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
