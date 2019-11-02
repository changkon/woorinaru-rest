package woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.user.Staff;
import woorinaru.rest.mapper.user.StaffMapper;
import woorinaru.rest.service.StaffService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private woorinaru.core.service.StaffService staffService;

    @Override
    @Transactional
    public int create(Staff staff) {
        StaffMapper mapper = StaffMapper.MAPPER;
        woorinaru.core.model.user.Staff staffModel = mapper.mapToModel(staff);
        return this.staffService.create(staffModel);
    }

    @Override
    @Transactional
    public Staff get(int id) {
        woorinaru.core.model.user.Staff staffModel = this.staffService.get(id);
        StaffMapper mapper = StaffMapper.MAPPER;
        Staff staffDto = mapper.mapToDto(staffModel);
        return staffDto;
    }

    @Override
    @Transactional
    public void delete(Staff staff) {
        StaffMapper mapper = StaffMapper.MAPPER;
        woorinaru.core.model.user.Staff staffModel = mapper.mapToModel(staff);
        this.staffService.delete(staffModel);
    }

    @Override
    @Transactional
    public void modify(Staff staff) {
        StaffMapper mapper = StaffMapper.MAPPER;
        woorinaru.core.model.user.Staff staffModel = mapper.mapToModel(staff);
        this.staffService.modify(staffModel);
    }

    @Override
    @Transactional
    public List<Staff> getAll() {
        List<woorinaru.core.model.user.Staff> staffModels = this.staffService.getAll();
        StaffMapper mapper = StaffMapper.MAPPER;
        List<Staff> staffDtos = staffModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return staffDtos;
    }

    public woorinaru.core.service.StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(woorinaru.core.service.StaffService staffService) {
        this.staffService = staffService;
    }
}
