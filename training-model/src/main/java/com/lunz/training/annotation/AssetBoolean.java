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
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BooleanValid.class)
public @interface AssetBoolean {

    String message() default "参数必须Boolean类型的true或者false";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
