package com.fokal.rideshare.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class ModelMapperConfig {
        @Bean
        public ModelMapper modelMapper() {
            ModelMapper mapper =  new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            mapper.getConfiguration().setAmbiguityIgnored(true);
            return mapper;
        }
    }

