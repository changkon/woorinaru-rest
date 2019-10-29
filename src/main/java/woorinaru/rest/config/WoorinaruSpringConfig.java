package woorinaru.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woorinaru.core.service.AdminService;
import woorinaru.core.service.AdminServiceImpl;
import woorinaru.repository.sql.dao.impl.AdminDaoImpl;

import javax.persistence.EntityManager;

@Configuration
public class WoorinaruSpringConfig {

    @Bean
    public AdminService adminService(EntityManager em) {
        AdminServiceImpl adminService = new AdminServiceImpl();
        adminService.setDao(new AdminDaoImpl(em));
        return adminService;
    }

}
