package com.dashboard.restservicedashboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
	@Bean
    public Docket encryptApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                 
                .apis(RequestHandlerSelectors.basePackage("com.restdashboard.restservicedashboard"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
	private ApiInfo metaData() {
        return new ApiInfo(
                "RestDashboard REST API",
                "RestserviceDashboard REST API for MobileApplicationDashboard",
                "2.0",
                "Terms of service",
                new Contact("Maurizio Pinzi", "https://about.me/mauriziopinzi", "maurizio.pinzi@microfocus.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}