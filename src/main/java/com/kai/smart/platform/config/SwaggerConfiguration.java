package com.kai.smart.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger을 초기화하는 Spring Boot Configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).host("www.koreaaero.com")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "KAI Smart Platform Data Extract API",
                "KAI Smart Platform Data Extract API",
                "1.0.0",
                "www.koreaaero.com",
                new Contact("Contact Me", "www.koreaaero.com", "foo@example.com"),
                "Licenses",
                "www.koreaaero.com",
                new ArrayList<>());
    }

}
