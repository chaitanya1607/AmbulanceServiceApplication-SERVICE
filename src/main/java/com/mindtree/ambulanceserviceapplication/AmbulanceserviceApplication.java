package com.mindtree.ambulanceserviceapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenAPIDefinition
@EnableJpaRepositories
@SpringBootApplication
@EnableSwagger2
public class AmbulanceserviceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AmbulanceserviceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AmbulanceserviceApplication.class, args);
		
	}
	

}
