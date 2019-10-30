package woorinaru.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import woorinaru.rest.dto.user.Admin;
import woorinaru.rest.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public Admin get(@PathVariable int id) {
        Optional<Admin> adminDtoOptional = this.adminService.get(id);
        Admin adminDto = adminDtoOptional.orElseGet(null);
        return adminDto;
    }

    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.getAll();
    }

    @PostMapping
    public void createAdmin(@RequestBody Admin admin) {
        this.adminService.create(admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Admin admin = new Admin();
        admin.setId(id);
        this.adminService.delete(admin);
    }

    @PutMapping
    public void modify(@RequestBody Admin admin) {
        this.adminService.modify(admin);
    }

}
