package com.woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woorinaru.rest.dto.management.administration.OutingClass;
import com.woorinaru.rest.mapper.management.administration.OutingClassMapper;
import com.woorinaru.rest.service.OutingClassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutingClassServiceImpl implements OutingClassService {

    @Autowired
    private com.woorinaru.core.service.OutingClassService outingClassService;

    @Override
    @Transactional
    public int create(OutingClass outingClass) {
        OutingClassMapper mapper = OutingClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.OutingClass outingClassModel = mapper.mapToModel(outingClass);
        return this.outingClassService.create(outingClassModel);
    }

    @Override
    @Transactional
    public OutingClass get(int id) {
        com.woorinaru.core.model.management.administration.OutingClass outingClassModel = this.outingClassService.get(id);
        OutingClassMapper mapper = OutingClassMapper.MAPPER;
        OutingClass outingClassDto = mapper.mapToDto(outingClassModel);
        return outingClassDto;
    }

    @Override
    @Transactional
    public void delete(OutingClass outingClass) {
        OutingClassMapper mapper = OutingClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.OutingClass outingClassModel = mapper.mapToModel(outingClass);
        this.outingClassService.delete(outingClassModel);
    }

    @Override
    @Transactional
    public void modify(OutingClass outingClass) {
        OutingClassMapper mapper = OutingClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.OutingClass outingClassModel = mapper.mapToModel(outingClass);
        this.outingClassService.modify(outingClassModel);
    }

    @Override
    @Transactional
    public List<OutingClass> getAll() {
        OutingClassMapper mapper = OutingClassMapper.MAPPER;
        List<com.woorinaru.core.model.management.administration.OutingClass> outingClassModels = this.outingClassService.getAll();
        List<OutingClass> outingClassDtos = outingClassModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return outingClassDtos;
    }

    public com.woorinaru.core.service.OutingClassService getOutingClassService() {
        return outingClassService;
    }

    public void setOutingClassService(com.woorinaru.core.service.OutingClassService outingClassService) {
        this.outingClassService = outingClassService;
    }
}
