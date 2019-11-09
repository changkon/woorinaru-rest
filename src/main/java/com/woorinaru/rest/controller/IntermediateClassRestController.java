package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.IntermediateClass;
import com.woorinaru.rest.service.IntermediateClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/class/intermediate")
public class IntermediateClassRestController {
    
    @Autowired
    private IntermediateClassService intermediateClassService;

    @GetMapping("/{id}")
    public ResponseEntity<IntermediateClass> get(@PathVariable int id) {
        IntermediateClass intermediateClassDto = this.intermediateClassService.get(id);
        return ResponseEntity.ok().body(intermediateClassDto);
    }

    @GetMapping
    public ResponseEntity<List<IntermediateClass>> getAll() {
        List<IntermediateClass> intermediateClassDtos = this.intermediateClassService.getAll();
        return ResponseEntity.ok().body(intermediateClassDtos);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody IntermediateClass intermediateClass) {
        int generatedId = this.intermediateClassService.create(intermediateClass);
        String uri = String.format("/woorinaru/api/class/intermediate/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        IntermediateClass intermediateClass = new IntermediateClass();
        intermediateClass.setId(id);
        this.intermediateClassService.delete(intermediateClass);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody IntermediateClass intermediateClass) {
        this.intermediateClassService.modify(intermediateClass);
        return ResponseEntity.ok().build();
    }

}
