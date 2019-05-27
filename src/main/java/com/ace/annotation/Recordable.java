package com.ace.annotation;


import java.lang.annotation.*;

/**
 * @author john
 * @date 19-5-23 下午5:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Recordable {
}
