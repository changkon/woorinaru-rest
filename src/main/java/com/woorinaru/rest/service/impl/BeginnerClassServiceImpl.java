package com.woorinaru.rest.service.impl;

import com.woorinaru.rest.dto.management.administration.BeginnerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woorinaru.rest.mapper.management.administration.BeginnerClassMapper;
import com.woorinaru.rest.service.BeginnerClassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeginnerClassServiceImpl implements BeginnerClassService {

    @Autowired
    private com.woorinaru.core.service.BeginnerClassService beginnerClassService;

    @Override
    @Transactional
    public int create(BeginnerClass beginnerClass) {
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.BeginnerClass beginnerClassModel = mapper.mapToModel(beginnerClass);
        return this.beginnerClassService.create(beginnerClassModel);
    }

    @Override
    @Transactional
    public BeginnerClass get(int id) {
        com.woorinaru.core.model.management.administration.BeginnerClass beginnerClassModel = this.beginnerClassService.get(id);
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        BeginnerClass beginnerClassDto = mapper.mapToDto(beginnerClassModel);
        return beginnerClassDto;
    }

    @Override
    @Transactional
    public void delete(BeginnerClass beginnerClass) {
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.BeginnerClass beginnerClassModel = mapper.mapToModel(beginnerClass);
        this.beginnerClassService.delete(beginnerClassModel);
    }

    @Override
    @Transactional
    public void modify(BeginnerClass beginnerClass) {
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.BeginnerClass beginnerClassModel = mapper.mapToModel(beginnerClass);
        this.beginnerClassService.modify(beginnerClassModel);
    }

    @Override
    @Transactional
    public List<BeginnerClass> getAll() {
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        List<com.woorinaru.core.model.management.administration.BeginnerClass> beginnerClassModels = this.beginnerClassService.getAll();
        List<BeginnerClass> beginnerClassDtos = beginnerClassModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return beginnerClassDtos;
    }

    public com.woorinaru.core.service.BeginnerClassService getBeginnerClassService() {
        return beginnerClassService;
    }

    public void setBeginnerClassService(com.woorinaru.core.service.BeginnerClassService beginnerClassService) {
        this.beginnerClassService = beginnerClassService;
    }
}
