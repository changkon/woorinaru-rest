package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.woorinaru.rest.service.TermService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/term")
public class TermRestController {

    @Autowired
    private TermService termService;

    @GetMapping("/{id}")
    public ResponseEntity<Term> get(@PathVariable int id) {
        Term termDto = this.termService.get(id);
        return ResponseEntity.ok().body(termDto);
    }

    @GetMapping
    public ResponseEntity<List<Term>> getAll() {
        List<Term> termDtos = this.termService.getAll();
        return ResponseEntity.ok().body(termDtos);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Term term) {
        int generatedId = this.termService.create(term);
        String uri = String.format("/woorinaru/api/term/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Term term = new Term();
        term.setId(id);
        this.termService.delete(term);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody Term term) {
        this.termService.modify(term);
        return ResponseEntity.ok().build();
    }
}
