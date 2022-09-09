package com.lunz.training.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author puritykid
 * @date 2021-07-20 17:40
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UuidValidator.class)
@Documented
public @interface Uuid {
    String message() default "参数不是uuid类型";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
