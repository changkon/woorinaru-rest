package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.user.Admin;
import com.woorinaru.rest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Admin> get(@PathVariable int id) {
        Admin adminDto = this.adminService.get(id);
        return ResponseEntity.ok().body(adminDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> adminDtos = adminService.getAll();
        return ResponseEntity.ok().body(adminDtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> create(@RequestBody Admin admin) {
        int generatedId = this.adminService.create(admin);
        String uri = String.format("/woorinaru/api/admin/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin') and @resourceOwnerPermissionEvaluator.hasPermissionExcludingRoles(#id)")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Admin admin = new Admin();
        admin.setId(id);
        this.adminService.delete(admin);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('admin') and @resourceOwnerPermissionEvaluator.hasPermissionExcludingRoles(#admin.getId())")
    public ResponseEntity<String> modify(@RequestBody Admin admin) {
        this.adminService.modify(admin);
        return ResponseEntity.ok().build();
    }

}
