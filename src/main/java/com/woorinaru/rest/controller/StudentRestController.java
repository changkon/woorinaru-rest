package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.user.Student;
import com.woorinaru.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Student> get(@PathVariable int id) {
        Student studentDto = this.studentService.get(id);
        return ResponseEntity.ok().body(studentDto);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentDtos = this.studentService.getAll();
        return ResponseEntity.ok().body(studentDtos);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('visitor')")
    public ResponseEntity<String> create(@RequestBody Student student) {
        int generatedId = this.studentService.create(student);
        String uri = String.format("/woorinaru/api/student/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('student')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Student student = new Student();
        student.setId(id);
        this.studentService.delete(student);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('student')")
    public ResponseEntity<String> modify(@RequestBody Student student) {
        this.studentService.modify(student);
        return ResponseEntity.ok().build();
    }

}
