package com.example.primejsf.config;

import com.example.primejsf.service.DistrictService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

/* Este code le indica a spring donde se encuentran los componentes */
@Configuration
@ComponentScan(basePackages = "com.example.primejsf" )
public class SpringConfig {

}
