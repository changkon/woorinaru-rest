package com.woorinaru.rest.controller;

import com.woorinaru.rest.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.woorinaru.rest.dto.user.Staff;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffRestController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/{id}")
    public ResponseEntity<Staff> get(@PathVariable int id) {
        Staff staffDto = this.staffService.get(id);
        return ResponseEntity.ok().body(staffDto);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAll() {
        List<Staff> staffDtos = this.staffService.getAll();
        return ResponseEntity.ok().body(staffDtos);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Staff staff) {
        int generatedId = this.staffService.create(staff);
        String uri = String.format("/woorinaru/api/staff/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Staff staff = new Staff();
        staff.setId(id);
        this.staffService.delete(staff);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody Staff staff) {
        this.staffService.modify(staff);
        return ResponseEntity.ok().build();
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }
}
