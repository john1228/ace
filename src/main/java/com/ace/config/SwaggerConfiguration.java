package com.ace.config;

import com.ace.entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author john
 * @date 19-5-8 下午3:22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo.DEFAULT)
                .ignoredParameterTypes(Account.class)
                .groupName("资源管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ace.controller.api"))
                .paths(PathSelectors.any())
                .build();
    }
}
