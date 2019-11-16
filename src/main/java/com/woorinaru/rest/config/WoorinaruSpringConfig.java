package com.woorinaru.rest.config;

import com.woorinaru.core.service.*;
import com.woorinaru.repository.sql.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class WoorinaruSpringConfig {

    @Bean
    public UserServiceImpl userService(EntityManager em) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDao(new UserDaoImpl(em));
        return userService;
    }

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

    @Bean
    public IntermediateClassService intermediateClassService(EntityManager em) {
        IntermediateClassServiceImpl intermediateClassService = new IntermediateClassServiceImpl();
        intermediateClassService.setDao(new IntermediateClassDaoImpl(em));
        return intermediateClassService;
    }

    @Bean
    public OutingClassService outingClassService(EntityManager em) {
        OutingClassServiceImpl outingClassService = new OutingClassServiceImpl();
        outingClassService.setDao(new OutingClassDaoImpl(em));
        return outingClassService;
    }

    @Bean
    public TutoringClassService tutoringClassService(EntityManager em) {
        TutoringClassServiceImpl tutoringClassService = new TutoringClassServiceImpl();
        tutoringClassService.setDao(new TutoringClassDaoImpl(em));
        return tutoringClassService;
    }
}
