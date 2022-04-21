//package com.mindtree.ambulanceserviceapplication.configuration.openapiconfiguration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.servers.Server;
//
//@Configuration
//public class OpenAPIConfiguration {
//	@Bean
//	public OpenAPI customOpenAPI() {
//		return new OpenAPI().addServersItem(new Server().url("/")).components(new Components()
//				.addSecuritySchemes("basic-auth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")));
//	}
//}
