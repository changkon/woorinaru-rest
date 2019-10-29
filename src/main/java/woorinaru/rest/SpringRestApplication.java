package woorinaru.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import woorinaru.rest.config.WebSecurityConfig;
import woorinaru.rest.config.WoorinaruSpringConfig;

@SpringBootApplication
@EntityScan(basePackages={"woorinaru.repository.sql.entity"})
@Import({WoorinaruSpringConfig.class, WebSecurityConfig.class})
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
