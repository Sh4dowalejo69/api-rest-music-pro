package cl.pro.music.rest.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"cl.pro.music.rest.api.repository"})
@EntityScan("cl.pro.music.rest.api.entity")
public class JpaConfiguration {

}
