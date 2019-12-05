package com.woorinaru.rest;

import com.woorinaru.rest.config.ProdWebSecurityConfig;
import com.woorinaru.rest.config.StageWebSecurityConfig;
import com.woorinaru.rest.config.WoorinaruSpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import com.woorinaru.rest.config.DevWebSecurityConfig;

@SpringBootApplication
@EntityScan(basePackages={"com.woorinaru.repository.sql.entity"})
@Import({WoorinaruSpringConfig.class, DevWebSecurityConfig.class, StageWebSecurityConfig.class, ProdWebSecurityConfig.class})
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
