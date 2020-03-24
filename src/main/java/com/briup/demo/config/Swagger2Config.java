package com.briup.demo.config;

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
 * swagger配置类
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.briup.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
        //为什么人家前台知道 
    }


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("看点咨询")
				.description("昆山杰普软件科技有限公司，http://www.briup.com")
				.termsOfServiceUrl("http://www.briup.com")
				.version("1.1")
				.build();
	}
}

