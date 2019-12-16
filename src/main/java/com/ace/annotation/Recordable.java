package com.ace.annotation;


import java.lang.annotation.*;

/**
 * 自定义注解，标示该请求是否要记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Recordable {
}
