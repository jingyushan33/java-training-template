package com.lunz.training.annotation;

import java.lang.annotation.*;

/**
 * @description: 日志注解
 * @author: purityKid
 * @create: 2022-06-16
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLog {
}
