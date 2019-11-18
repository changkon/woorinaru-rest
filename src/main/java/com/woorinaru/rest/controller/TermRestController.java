package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.Term;
import com.woorinaru.rest.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/term")
public class TermRestController {

    @Autowired
    private TermService termService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Term> get(@PathVariable int id) {
        Term termDto = this.termService.get(id);
        return ResponseEntity.ok().body(termDto);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Term>> getAll() {
        List<Term> termDtos = this.termService.getAll();
        return ResponseEntity.ok().body(termDtos);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin', 'leader')")
    public ResponseEntity<String> create(@RequestBody Term term) {
        int generatedId = this.termService.create(term);
        String uri = String.format("/woorinaru/api/term/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin', 'leader')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Term term = new Term();
        term.setId(id);
        this.termService.delete(term);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('admin', 'leader')")
    public ResponseEntity<String> modify(@RequestBody Term term) {
        this.termService.modify(term);
        return ResponseEntity.ok().build();
    }
}
