package woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.management.administration.IntermediateClass;
import woorinaru.rest.dto.management.administration.IntermediateClass;
import woorinaru.rest.mapper.management.administration.IntermediateClassMapper;
import woorinaru.rest.service.IntermediateClassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntermediateClassServiceImpl implements IntermediateClassService {

    @Autowired
    private woorinaru.core.service.IntermediateClassService intermediateClassService;

    @Override
    @Transactional
    public int create(IntermediateClass intermediateClass) {
        IntermediateClassMapper mapper = IntermediateClassMapper.MAPPER;
        woorinaru.core.model.management.administration.IntermediateClass intermediateClassModel = mapper.mapToModel(intermediateClass);
        return this.intermediateClassService.create(intermediateClassModel);
    }

    @Override
    @Transactional
    public IntermediateClass get(int id) {
        woorinaru.core.model.management.administration.IntermediateClass intermediateClassModel = this.intermediateClassService.get(id);
        IntermediateClassMapper mapper = IntermediateClassMapper.MAPPER;
        IntermediateClass intermediateClassDto = mapper.mapToDto(intermediateClassModel);
        return intermediateClassDto;
    }

    @Override
    @Transactional
    public void delete(IntermediateClass intermediateClass) {
        IntermediateClassMapper mapper = IntermediateClassMapper.MAPPER;
        woorinaru.core.model.management.administration.IntermediateClass intermediateClassModel = mapper.mapToModel(intermediateClass);
        this.intermediateClassService.delete(intermediateClassModel);
    }

    @Override
    @Transactional
    public void modify(IntermediateClass intermediateClass) {
        IntermediateClassMapper mapper = IntermediateClassMapper.MAPPER;
        woorinaru.core.model.management.administration.IntermediateClass intermediateClassModel = mapper.mapToModel(intermediateClass);
        this.intermediateClassService.modify(intermediateClassModel);
    }

    @Override
    @Transactional
    public List<IntermediateClass> getAll() {
        IntermediateClassMapper mapper = IntermediateClassMapper.MAPPER;
        List<woorinaru.core.model.management.administration.IntermediateClass> intermediateClassModels = this.intermediateClassService.getAll();
        List<IntermediateClass> intermediateClassDtos = intermediateClassModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return intermediateClassDtos;
    }

    public woorinaru.core.service.IntermediateClassService getIntermediateClassService() {
        return intermediateClassService;
    }

    public void setIntermediateClassService(woorinaru.core.service.IntermediateClassService intermediateClassService) {
        this.intermediateClassService = intermediateClassService;
    }
}
