package woorinaru.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woorinaru.core.service.*;
import woorinaru.repository.sql.dao.impl.AdminDaoImpl;
import woorinaru.repository.sql.dao.impl.ResourceDaoImpl;
import woorinaru.repository.sql.dao.impl.StudentDaoImpl;

import javax.persistence.EntityManager;

@Configuration
public class WoorinaruSpringConfig {

    @Bean
    public StudentService studentService(EntityManager em) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setDao(new StudentDaoImpl(em));
        return studentService;
    }

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
