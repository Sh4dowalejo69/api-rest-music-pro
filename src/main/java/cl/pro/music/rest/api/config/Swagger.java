package cl.pro.music.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Controller
@Configuration
public class Swagger {
	
	@GetMapping("/")
	public String redirectToSwaggerUi() {
		return "redirect:/swagger-ui/";
	}
	
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cl.pro.music.rest.api.controller"))
				.build()
				.apiInfo(apiInfo());
	}
	
	ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
				.title("api-rest-music-pro")
				.description("Api rest de music pro")
				.license("")
				.licenseUrl("http://unlicense.org")
				.termsOfServiceUrl("http://www.musicpro.cl")
				.version("1.0.0")
				.contact(new Contact("", "", ""))
				.build();
	}
	
	@Bean
	public OpenAPI openApi() {
		
		return new OpenAPI().info(
			new Info()
				.title("Api rest de music pro")
				.description("Api rest para empresa music pro")
				.termsOfService("http://www.musicpro.cl")
				.version("1.0.0")
				.license(new License().name("").url("http://unlicense.org"))
				.contact(new io.swagger.v3.oas.models.info.Contact().email(""))
			);
		
		
	}
}
