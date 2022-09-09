package com.lunz.training.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自定一配置扫描（额外需要引入项目的配置可以在这里配置）
 * @author: purityKid
 * @create: 2022-08-02
 **/
@Configuration
@ComponentScan(basePackages = "com.lunz.http")
public class CustomConfigScan {
}
