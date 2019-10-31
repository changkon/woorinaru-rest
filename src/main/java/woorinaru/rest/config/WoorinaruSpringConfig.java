package woorinaru.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woorinaru.core.service.AdminService;
import woorinaru.core.service.AdminServiceImpl;
import woorinaru.core.service.ResourceService;
import woorinaru.core.service.ResourceServiceImpl;
import woorinaru.repository.sql.dao.impl.AdminDaoImpl;
import woorinaru.repository.sql.dao.impl.ResourceDaoImpl;

import javax.persistence.EntityManager;

@Configuration
public class WoorinaruSpringConfig {

    @Bean
    public AdminService adminService(EntityManager em) {
        AdminServiceImpl adminService = new AdminServiceImpl();
        adminService.setDao(new AdminDaoImpl(em));
        return adminService;
    }

    @Bean
    public ResourceService resourceService(EntityManager em) {
        ResourceServiceImpl resourceService = new ResourceServiceImpl();
        resourceService.setDao(new ResourceDaoImpl(em));
        return resourceService;
    }

}
