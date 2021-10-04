package com.ics.icsoauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

@Configuration
public class IDGeneratedConfig {
    @Bean
    public IdGenerator idGenerator(){
        return new JdkIdGenerator();
    }
}
