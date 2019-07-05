package com.ace.annotation;


import com.ace.annotation.impl.EnumValidatorImpl;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author john
 * @date 19-5-23 下午5:02
 */
@Constraint(validatedBy = EnumValidatorImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterName {
    String value() default "";
}
