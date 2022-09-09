package com.lunz.training.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllowAnonymous {
    String message() default "匿名访问";
    boolean required() default true;
}
