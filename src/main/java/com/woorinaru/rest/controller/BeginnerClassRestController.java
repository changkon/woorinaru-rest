package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.BeginnerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.woorinaru.rest.service.BeginnerClassService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/class/beginner")
public class BeginnerClassRestController {

    @Autowired
    private BeginnerClassService beginnerClassService;

    @GetMapping("/{id}")
    public ResponseEntity<BeginnerClass> get(@PathVariable int id) {
        BeginnerClass beginnerClassDto = this.beginnerClassService.get(id);
        return ResponseEntity.ok().body(beginnerClassDto);
    }

    @GetMapping
    public ResponseEntity<List<BeginnerClass>> getAll() {
        List<BeginnerClass> beginnerClassDtos = this.beginnerClassService.getAll();
        return ResponseEntity.ok().body(beginnerClassDtos);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BeginnerClass beginnerClass) {
        int generatedId = this.beginnerClassService.create(beginnerClass);
        String uri = String.format("/woorinaru/api/class/beginner/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        BeginnerClass beginnerClass = new BeginnerClass();
        beginnerClass.setId(id);
        this.beginnerClassService.delete(beginnerClass);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody BeginnerClass beginnerClass) {
        this.beginnerClassService.modify(beginnerClass);
        return ResponseEntity.ok().build();
    }
}
