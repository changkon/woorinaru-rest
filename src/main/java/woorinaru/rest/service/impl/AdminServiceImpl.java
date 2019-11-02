package woorinaru.rest.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.user.Admin;
import woorinaru.rest.mapper.user.AdminMapper;
import woorinaru.rest.service.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private woorinaru.core.service.AdminService adminService;

    public AdminServiceImpl() {}

    @Override
    @Transactional
    public int create(Admin admin) {
        AdminMapper mapper = AdminMapper.MAPPER;
        woorinaru.core.model.user.Admin adminModel = mapper.mapToModel(admin);
        return this.adminService.create(adminModel);
    }

    @Override
    @Transactional
    public Admin get(int id) {
        woorinaru.core.model.user.Admin adminModel = this.adminService.get(id);
        AdminMapper mapper = AdminMapper.MAPPER;
        Admin adminDto = mapper.mapToDto(adminModel);
        return adminDto;
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        AdminMapper mapper = AdminMapper.MAPPER;
        woorinaru.core.model.user.Admin adminModel = mapper.mapToModel(admin);
        this.adminService.delete(adminModel);
    }

    @Override
    @Transactional
    public void modify(Admin admin) {
        AdminMapper mapper = AdminMapper.MAPPER;
        woorinaru.core.model.user.Admin adminModel = mapper.mapToModel(admin);
        this.adminService.modify(adminModel);
    }

    @Override
    @Transactional
    public List<Admin> getAll() {
        List<woorinaru.core.model.user.Admin> adminModels = this.adminService.getAll();
        AdminMapper mapper = AdminMapper.MAPPER;
        List<Admin> adminDtos = adminModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return adminDtos;
    }

    public woorinaru.core.service.AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(woorinaru.core.service.AdminService adminService) {
        this.adminService = adminService;
    }
}
