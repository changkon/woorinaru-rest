package com.woorinaru.rest;

import com.woorinaru.rest.config.WoorinaruSpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import com.woorinaru.rest.config.WebSecurityConfig;

@SpringBootApplication
@EntityScan(basePackages={"com.woorinaru.repository.sql.entity"})
@Import({WoorinaruSpringConfig.class, WebSecurityConfig.class})
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
