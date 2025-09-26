package com.project.vms.vmsproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VmsConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
