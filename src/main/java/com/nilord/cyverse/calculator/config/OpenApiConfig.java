package com.nilord.cyverse.calculator.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuration for swagger generation
 * 
 * @author nilord
 *
 */
@Configuration
public class OpenApiConfig {
	
	public OpenAPI springShopOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("Cyverse Calculator API")
              .description("Calculator api for cyverse game")
              .version("v0.0.1")
              .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }

}
