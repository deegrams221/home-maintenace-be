package com.homemaintenance.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.homemaintenance.app"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(Pageable.class)
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Home Maintenance App")
                .description("Home Maintenance App")
                .contact(new Contact("Diana Grams", "https://dianagrams.dev", "deegrams221@gmail.com"))
                .version("1.0.0").build();
    }
}
