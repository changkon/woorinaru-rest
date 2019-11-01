package woorinaru.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorinaru.rest.dto.user.Student;
import woorinaru.rest.service.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable int id) {
        Student studentDto = this.studentService.get(id);
        return ResponseEntity.ok().body(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentDtos = this.studentService.getAll();
        return ResponseEntity.ok().body(studentDtos);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Student student) {
        int generatedId = this.studentService.create(student);
        String uri = String.format("/woorinaru/api/student/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Student student = new Student();
        student.setId(id);
        this.studentService.delete(student);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody Student student) {
        this.studentService.modify(student);
        return ResponseEntity.ok().build();
    }

}
