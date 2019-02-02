package com.ace.entity.room;

import com.ace.annotation.EnumValidator;
import com.ace.util.AttributeType;

import javax.validation.constraints.NotEmpty;


public class Attribute {
    private Integer id;
    @NotEmpty(message = "属性名不能为空")
    private String name;
    @EnumValidator(enumClazz = AttributeType.class, message = "非法的属性类别")
    private String type;
    @NotEmpty(message = "属性值不能为空")
    private String value;

    public Attribute() {
    }

    public Attribute(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
