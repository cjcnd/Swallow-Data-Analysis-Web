package org.example.swllow_data_analysis.config;

import org.example.swllow_data_analysis.mathFuntion.MathFunction;
import org.example.swllow_data_analysis.mathFuntion.haversine.Haversine;
import org.example.swllow_data_analysis.storage.StorageProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.swllow_data_analysis.mathFuntion.haversine")
public class ProjectConfig {

    @Bean
    StorageProperties StorageProperties() {
        return new StorageProperties();
    }

    @Bean
    MathFunction mathFunction() {
        return new Haversine();
    }
}
