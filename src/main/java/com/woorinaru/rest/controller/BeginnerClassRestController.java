package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.BeginnerClass;
import com.woorinaru.rest.service.BeginnerClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/class/beginner")
public class BeginnerClassRestController {

    @Autowired
    private BeginnerClassService beginnerClassService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<BeginnerClass> get(@PathVariable int id) {
        BeginnerClass beginnerClassDto = this.beginnerClassService.get(id);
        return ResponseEntity.ok().body(beginnerClassDto);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<BeginnerClass>> getAll() {
        List<BeginnerClass> beginnerClassDtos = this.beginnerClassService.getAll();
        return ResponseEntity.ok().body(beginnerClassDtos);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin', 'leader', 'vice_leader', 'sub_leader', 'teacher')")
    public ResponseEntity<String> create(@RequestBody BeginnerClass beginnerClass) {
        int generatedId = this.beginnerClassService.create(beginnerClass);
        String uri = String.format("/woorinaru/api/class/beginner/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin', 'leader')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        BeginnerClass beginnerClass = new BeginnerClass();
        beginnerClass.setId(id);
        this.beginnerClassService.delete(beginnerClass);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('admin', 'leader', 'vice_leader', 'sub_leader', 'teacher')")
    public ResponseEntity<String> modify(@RequestBody BeginnerClass beginnerClass) {
        this.beginnerClassService.modify(beginnerClass);
        return ResponseEntity.ok().build();
    }
}
