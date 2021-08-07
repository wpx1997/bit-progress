package com.wpx.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.wpx.property.SwaggerProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.wpx.constant.VerifyConstant.*;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@EnableOpenApi
@EnableKnife4j
@Configuration
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnable())
                .groupName(swaggerProperties.getGroup())
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getApplicationName())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey authorization = new ApiKey(AUTHORIZATION, AUTHORIZATION, HEADER);
        ApiKey routeApiToken = new ApiKey(ROUTE_API_TOKEN, ROUTE_API_TOKEN, HEADER);
        ApiKey routeRestToken = new ApiKey(ROUTE_REST_TOKEN, ROUTE_REST_TOKEN, HEADER);
        ApiKey userId = new ApiKey(USER_ID, USER_ID, HEADER);
        List<SecurityScheme> apiKeys = new ArrayList<>();
        apiKeys.add(authorization);
        apiKeys.add(routeApiToken);
        apiKeys.add(routeRestToken);
        apiKeys.add(userId);
        return apiKeys;
    }

}