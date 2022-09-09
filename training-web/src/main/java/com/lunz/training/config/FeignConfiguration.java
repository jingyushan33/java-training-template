package com.lunz.training.config;

import com.lunz.training.inceptor.FeignRequestInterceptor;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName: FeignConfiguration
 * @Description: feign 配置
 * @Author: puritykid
 * @Date: 2021/6/30 15:11
 * @Version: v1.0
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志配置
     */
    @Bean
    public feign.Logger feignLevel() {
        return new Slf4jLogger();
    }


    /**
     * feign 请求头配置
     */
    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

}