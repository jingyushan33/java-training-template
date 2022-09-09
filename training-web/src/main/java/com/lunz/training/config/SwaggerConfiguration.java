package com.lunz.training.config;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * swagger配置
 * @author haha
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    /**
     * 获取token的地址
     */
    @Value("${config.oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${config.oauth2.swagger.title}")
    private String swaggerTitle;

    @Value("${config.oauth2.swagger.description}")
    private String swaggerDescription;

    @Value("${config.oauth2.swagger.version}")
    private String swaggerVersion;

    @Bean
    public Docket uiRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                // 安全上下文配置
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Lists.newArrayList(securitySchema()));

    }
    /**
     * 认证方式
     * @return SecurityScheme
     */
    private SecurityScheme securitySchema() {


        return new OAuthBuilder()
                .name("oauth2")
                .grantTypes(Collections.singletonList(new ResourceOwnerPasswordCredentialsGrant(accessTokenUri)))
                .scopes(Collections.emptyList())
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().build();
    }


    /**
     * 文档描述
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerTitle).contact(new Contact("共享研发陈晓军","","chenxiaojun@zhongruigroup.com")).version(swaggerVersion).description(swaggerDescription)
                .build();
    }
}
