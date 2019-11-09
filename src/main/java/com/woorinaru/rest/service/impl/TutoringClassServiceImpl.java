package com.woorinaru.rest.service.impl;

import com.woorinaru.rest.service.TutoringClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woorinaru.rest.dto.management.administration.TutoringClass;
import com.woorinaru.rest.mapper.management.administration.TutoringClassMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutoringClassServiceImpl implements TutoringClassService {

    @Autowired
    private com.woorinaru.core.service.TutoringClassService tutoringClassService;

    @Override
    @Transactional
    public int create(TutoringClass tutoringClass) {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = mapper.mapToModel(tutoringClass);
        return this.tutoringClassService.create(tutoringClassModel);
    }

    @Override
    @Transactional
    public TutoringClass get(int id) {
        com.woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = this.tutoringClassService.get(id);
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        TutoringClass tutoringClassDto = mapper.mapToDto(tutoringClassModel);
        return tutoringClassDto;
    }

    @Override
    @Transactional
    public void delete(TutoringClass tutoringClass) {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = mapper.mapToModel(tutoringClass);
        this.tutoringClassService.delete(tutoringClassModel);
    }

    @Override
    @Transactional
    public void modify(TutoringClass tutoringClass) {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        com.woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = mapper.mapToModel(tutoringClass);
        this.tutoringClassService.modify(tutoringClassModel);
    }

    @Override
    @Transactional
    public List<TutoringClass> getAll() {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        List<com.woorinaru.core.model.management.administration.TutoringClass> tutoringClassModels = this.tutoringClassService.getAll();
        List<TutoringClass> tutoringClassDtos = tutoringClassModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return tutoringClassDtos;
    }

    public com.woorinaru.core.service.TutoringClassService getTutoringClassService() {
        return tutoringClassService;
    }

    public void setTutoringClassService(com.woorinaru.core.service.TutoringClassService tutoringClassService) {
        this.tutoringClassService = tutoringClassService;
    }
}
