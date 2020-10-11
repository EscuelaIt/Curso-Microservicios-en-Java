package es.edu.escuela_it.microservices.configurations;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfo("EscuelaIT", "Microservice", "1.0", "http://escuelait.com",
						new Contact("Rafael Benedettelli", "escuelait.com", "escuelait@gmail.com"),
						"Uso exclusivo EscuelaIT", "http://anfix.com", Collections.emptyList()));

	}

	
}
