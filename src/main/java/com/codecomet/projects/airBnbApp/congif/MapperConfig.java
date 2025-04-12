package com.codecomet.projects.airBnbApp.congif;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
