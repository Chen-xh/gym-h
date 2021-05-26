package com.chen.gym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SWAGGER配置类
 * @author CHEN
 * @date: 2020-12-09 11:10
 * 这是后台与前端对接的接口文档
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket UserApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("User模块接口文档")
                .apiInfo(Api())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chen.gym.controller.user"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket ContestApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Contest模块接口文档")
                .apiInfo(Api())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chen.gym.controller.contest"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket FieldApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Field模块接口文档")
                .apiInfo(Api())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chen.gym.controller.field"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket EquipmentApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Equipment模块接口文档")
                .apiInfo(Api())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chen.gym.controller.equipment"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo Api() {
        return new ApiInfoBuilder()
                .title("体育馆管理系统")
                .description("体育馆管理系统接口文档，共4个模块")
                .termsOfServiceUrl("http://118.178.125.139:9877")
                .version("1.0")
                .build();
    }
}
