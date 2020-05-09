package com.framework;

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
 * @author lmx
 * @date 2020-05-08 17:20
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private String basePackage=""; // controller接口所在的包

    private String title="lmx工程";   // 当前文档的标题

    private String description="swagger通用配置在framework-swagger模块,需要使用的模块需要完成以下配置:\n1在pom中引入本模块\n2配置一个配置类(@Configuration)继承本模块的SwaggerConfig类"; // 当前文档的详细描述

    private String version="版本2020/05/09"; // 当前文档的版本

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }

}
