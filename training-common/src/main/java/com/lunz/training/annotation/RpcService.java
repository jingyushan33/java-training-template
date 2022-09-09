package com.lunz.training.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName: RpcService
 * @Description: 远程调用注解
 * @Author: puritykid
 * @Date: 2021/7/1 16:33
 * @Version: v1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcService {
}
