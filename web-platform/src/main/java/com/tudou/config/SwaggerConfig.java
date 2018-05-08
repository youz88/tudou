package com.tudou.config;

import com.google.common.base.Predicates;
import com.tudou.common.Const;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@ConditionalOnClass({ Docket.class, ApiInfo.class, EnableSwagger2.class })
@EnableSwagger2
public class SwaggerConfig {

    @Value("${online:false}")
    private boolean online;

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 非线上环境时启用 springfox
                .apiInfo(apiInfo()).enable(!online).useDefaultResponseMessages(false)
                .select().paths(Predicates.not(PathSelectors.regex("/error")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("TUDOU", "接口文档", Const.DUBBO_VERSION, "",
                new Contact("youz", "", ""), "", "",
                new ArrayList<>());
    }
}
