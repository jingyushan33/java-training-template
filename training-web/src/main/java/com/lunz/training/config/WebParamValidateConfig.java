package com.lunz.training.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * web 参数验证配置
 * @author puritykid
 * @date 2021-08-03 16:38
 */
@Configuration
public class WebParamValidateConfig {


    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 关闭快速失败
                .failFast(false)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator());
        methodValidationPostProcessor.setProxyTargetClass(true);
        return methodValidationPostProcessor;
    }
}
