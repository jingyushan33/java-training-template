package com.lunz.training.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author puritykid
 * @date 2021-07-22 14:03
 */
public class BooleanValid implements ConstraintValidator<AssetBoolean, Boolean> {
    @Override
    public void initialize(AssetBoolean constraint) {
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value == null || value == false || value;
    }
}
