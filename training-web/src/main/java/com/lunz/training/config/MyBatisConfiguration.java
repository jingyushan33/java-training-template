package com.lunz.training.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.lunz.util.GlobalFieldValidator;
import com.lunz.util.WrapperFieldConvert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * mybatis配置
 *
 * @author haha
 */
@Configuration
@MapperScan(basePackages = "com.lunz.training.mapper")
public class MyBatisConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInterceptor());
        return interceptor;
    }

    /**
     * 分页插件，自动识别数据库类型
     *
     * @return
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置最大页码数量，默认500条，-1不受限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        return paginationInnerInterceptor;
    }

    /**
     * 全局字段校验器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public GlobalFieldValidator globalFieldValidator() {
        return new GlobalFieldValidator();
    }

    /**
     * 驼峰字段转换器
     *
     * @return
     */
    @Bean
    public WrapperFieldConvert wrapperFieldConvert() {
        return new WrapperFieldConvert();
    }
}
