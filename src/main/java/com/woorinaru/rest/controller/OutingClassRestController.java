package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.OutingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.woorinaru.rest.service.OutingClassService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/class/outing")
public class OutingClassRestController {

    @Autowired
    private OutingClassService outingClassService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<OutingClass> get(@PathVariable int id) {
        OutingClass outingClassDto = this.outingClassService.get(id);
        return ResponseEntity.ok().body(outingClassDto);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<OutingClass>> getAll() {
        List<OutingClass> outingClassDtos = this.outingClassService.getAll();
        return ResponseEntity.ok().body(outingClassDtos);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin', 'leader', 'vice_leader', 'sub_leader', 'teacher')")
    public ResponseEntity<String> create(@RequestBody OutingClass outingClass) {
        int generatedId = this.outingClassService.create(outingClass);
        String uri = String.format("/woorinaru/api/class/outing/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin', 'leader')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        OutingClass outingClass = new OutingClass();
        outingClass.setId(id);
        this.outingClassService.delete(outingClass);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('admin', 'leader', 'vice_leader', 'sub_leader', 'teacher')")
    public ResponseEntity<String> modify(@RequestBody OutingClass outingClass) {
        this.outingClassService.modify(outingClass);
        return ResponseEntity.ok().build();
    }

}
