package com.woorinaru.rest.service.impl;

import com.woorinaru.rest.dto.user.Student;
import com.woorinaru.rest.mapper.user.StudentMapper;
import com.woorinaru.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private com.woorinaru.core.service.StudentService studentService;

    public StudentServiceImpl() {}

    @Override
    @Transactional
    public int create(Student student) {
        StudentMapper mapper = StudentMapper.MAPPER;
        com.woorinaru.core.model.user.Student studentModel = mapper.mapToModel(student);
        return this.studentService.create(studentModel);
    }

    @Override
    @Transactional
    public Student get(int id) {
        com.woorinaru.core.model.user.Student studentModel = this.studentService.get(id);
        StudentMapper mapper = StudentMapper.MAPPER;
        Student studentDto = mapper.mapToDto(studentModel);
        return studentDto;
    }

    @Override
    @Transactional
    public void delete(Student student) {
        StudentMapper mapper = StudentMapper.MAPPER;
        com.woorinaru.core.model.user.Student studentModel = mapper.mapToModel(student);
        this.studentService.delete(studentModel);
    }

    @Override
    @Transactional
    public void modify(Student student) {
        StudentMapper mapper = StudentMapper.MAPPER;
        com.woorinaru.core.model.user.Student studentModel = mapper.mapToModel(student);
        this.studentService.modify(studentModel);
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        List<com.woorinaru.core.model.user.Student> studentModels = this.studentService.getAll();
        StudentMapper mapper = StudentMapper.MAPPER;
        List<Student> studentDtos = studentModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return studentDtos;
    }

    public com.woorinaru.core.service.StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(com.woorinaru.core.service.StudentService studentService) {
        this.studentService = studentService;
    }
}
