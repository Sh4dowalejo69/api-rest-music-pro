package cl.pro.music.rest.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@ComponentScan("cl.pro.music.rest.api")
@EnableOpenApi
public class ApiRestMusicProApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestMusicProApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
