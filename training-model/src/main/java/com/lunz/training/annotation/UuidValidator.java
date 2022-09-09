package com.lunz.training.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

/**
 * uuid校验
 *
 * @author puritykid
 * @date 2021-07-20 17:40
 */
public class UuidValidator implements ConstraintValidator<Uuid, String> {


    private static final String UUID_REGEXP = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    @Override
    public void initialize(Uuid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return compile(UUID_REGEXP, CASE_INSENSITIVE).matcher(value).matches();
    }
}
