package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.TutoringClass;
import com.woorinaru.rest.service.TutoringClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/class/tutoring")
public class TutoringClassRestController {

    @Autowired
    private TutoringClassService tutoringClassService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TutoringClass> get(@PathVariable int id) {
        TutoringClass tutoringClassDto = this.tutoringClassService.get(id);
        return ResponseEntity.ok().body(tutoringClassDto);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TutoringClass>> getAll() {
        List<TutoringClass> tutoringClassDtos = this.tutoringClassService.getAll();
        return ResponseEntity.ok().body(tutoringClassDtos);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin', 'leader', 'vice_leader', 'sub_leader', 'teacher')")
    public ResponseEntity<String> create(@RequestBody TutoringClass tutoringClass) {
        int generatedId = this.tutoringClassService.create(tutoringClass);
        String uri = String.format("/woorinaru/api/class/beginner/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin', 'leader')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        TutoringClass tutoringClass = new TutoringClass();
        tutoringClass.setId(id);
        this.tutoringClassService.delete(tutoringClass);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('admin', 'leader', 'vice_leader', 'sub_leader', 'teacher')")
    public ResponseEntity<String> modify(@RequestBody TutoringClass tutoringClass) {
        this.tutoringClassService.modify(tutoringClass);
        return ResponseEntity.ok().build();
    }

}
