package woorinaru.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woorinaru.core.service.*;
import woorinaru.repository.sql.dao.impl.*;

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
    public StaffService staffService(EntityManager em) {
        StaffServiceImpl staffService = new StaffServiceImpl();
        staffService.setDao(new StaffDaoImpl(em));
        return staffService;
    }

    @Bean
    public ResourceService resourceService(EntityManager em) {
        ResourceServiceImpl resourceService = new ResourceServiceImpl();
        resourceService.setDao(new ResourceDaoImpl(em));
        return resourceService;
    }

    @Bean
    public TermService termService(EntityManager em) {
        TermServiceImpl termService = new TermServiceImpl();
        termService.setDao(new TermDaoImpl(em));
        return termService;
    }

    @Bean
    public EventService eventService(EntityManager em) {
        EventServiceImpl eventService = new EventServiceImpl();
        eventService.setDao(new EventDaoImpl(em));
        return eventService;
    }

    @Bean
    public BeginnerClassService beginnerClassService(EntityManager em) {
        BeginnerClassServiceImpl beginnerClassService = new BeginnerClassServiceImpl();
        beginnerClassService.setDao(new BeginnerClassDaoImpl(em));
        return beginnerClassService;
    }
}
