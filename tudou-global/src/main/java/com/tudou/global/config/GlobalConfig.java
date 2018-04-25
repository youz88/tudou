package com.tudou.global.config;

import com.tudou.common.util.ApplicationContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public ApplicationContextUtil setupApplicationContext() {
        return new ApplicationContextUtil();
    }
}
