package com.lunz.training.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * @author puritykid
 * @date 2021-07-20 17:34
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Date19Valid.class)
public @interface Date19 {

    String message() default "日期不合法!日期格式必须是[yyyy-MM-dd HH:mm:ss]!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
