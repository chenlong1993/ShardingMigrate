package com.sharding.migrate.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
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

/**
 * @ClassName Knife4JConfig
 * @Author ：daiyu
 * @Date 2022-12-03 09:28
 * @Description：
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class Knife4JConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).enableUrlTemplating(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Knife4j接口文档")
                .description("更多请咨询felord.cn")
                .contact(new Contact("迁移", "xxx", "xxx"))
                .version("1.0.0")
                .build();
    }
    
}
