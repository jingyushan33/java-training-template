package com.lunz.training.annotation;

import java.lang.annotation.*;

/**
 * @author puritykid
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDesc {
    String value() default "";
}
