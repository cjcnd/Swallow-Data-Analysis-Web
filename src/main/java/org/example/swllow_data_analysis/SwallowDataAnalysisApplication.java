package org.example.swllow_data_analysis;

import org.example.swllow_data_analysis.service.FileInputService;
import org.example.swllow_data_analysis.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SwallowDataAnalysisApplication implements WebMvcConfigurer {

	public static void main(String[] args) {SpringApplication.run(SwallowDataAnalysisApplication.class, args);}

	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("home.html");
	}

	@Bean
	CommandLineRunner init(FileInputService fileInputService) {
		return (args) -> {
			fileInputService.deleteAll();
			fileInputService.init();
		};
	}
}
