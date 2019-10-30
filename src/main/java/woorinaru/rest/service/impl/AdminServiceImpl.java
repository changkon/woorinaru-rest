package woorinaru.rest.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import woorinaru.core.command.UpdateCommand;
import woorinaru.rest.dto.user.Admin;
import woorinaru.rest.mapper.user.AdminMapper;
import woorinaru.rest.service.AdminService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private woorinaru.core.service.AdminService adminService;

    @Autowired
    private EntityManager em;

    public AdminServiceImpl() {}

    @Override
    @Transactional
    public void create(Admin admin) {
        AdminMapper mapper = Mappers.getMapper(AdminMapper.class);
        woorinaru.core.model.user.Admin adminModel = mapper.mapToModel(admin);
        this.adminService.create(adminModel);
    }

    @Override
    @Transactional
    public Optional<Admin> get(int id) {
        Optional<woorinaru.core.model.user.Admin> adminModelOptional = this.adminService.get(id);
        Optional<Admin> adminDtoOptional = Optional.empty();
        if (adminModelOptional.isPresent()) {
            AdminMapper mapper = Mappers.getMapper(AdminMapper.class);
            adminDtoOptional = Optional.of(mapper.mapToDto(adminModelOptional.get()));
        }
        return adminDtoOptional;
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        AdminMapper mapper = Mappers.getMapper(AdminMapper.class);
        woorinaru.core.model.user.Admin adminModel = mapper.mapToModel(admin);
        this.adminService.delete(adminModel);
    }

    @Override
    @Transactional
    public void modify(Admin admin) {
        AdminMapper mapper = Mappers.getMapper(AdminMapper.class);
        woorinaru.core.model.user.Admin adminModel = mapper.mapToModel(admin);
        this.adminService.modify(adminModel);
    }

    @Override
    @Transactional
    public List<Admin> getAll() {
        List<woorinaru.core.model.user.Admin> adminModels = this.adminService.getAll();
        AdminMapper mapper = Mappers.getMapper(AdminMapper.class);
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
