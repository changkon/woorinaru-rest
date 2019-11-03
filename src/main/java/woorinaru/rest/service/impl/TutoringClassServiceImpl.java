package woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.management.administration.TutoringClass;
import woorinaru.rest.mapper.management.administration.TutoringClassMapper;
import woorinaru.rest.service.TutoringClassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutoringClassServiceImpl implements TutoringClassService {

    @Autowired
    private woorinaru.core.service.TutoringClassService tutoringClassService;

    @Override
    @Transactional
    public int create(TutoringClass tutoringClass) {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = mapper.mapToModel(tutoringClass);
        return this.tutoringClassService.create(tutoringClassModel);
    }

    @Override
    @Transactional
    public TutoringClass get(int id) {
        woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = this.tutoringClassService.get(id);
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        TutoringClass tutoringClassDto = mapper.mapToDto(tutoringClassModel);
        return tutoringClassDto;
    }

    @Override
    @Transactional
    public void delete(TutoringClass tutoringClass) {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = mapper.mapToModel(tutoringClass);
        this.tutoringClassService.delete(tutoringClassModel);
    }

    @Override
    @Transactional
    public void modify(TutoringClass tutoringClass) {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        woorinaru.core.model.management.administration.TutoringClass tutoringClassModel = mapper.mapToModel(tutoringClass);
        this.tutoringClassService.modify(tutoringClassModel);
    }

    @Override
    @Transactional
    public List<TutoringClass> getAll() {
        TutoringClassMapper mapper = TutoringClassMapper.MAPPER;
        List<woorinaru.core.model.management.administration.TutoringClass> tutoringClassModels = this.tutoringClassService.getAll();
        List<TutoringClass> tutoringClassDtos = tutoringClassModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return tutoringClassDtos;
    }

    public woorinaru.core.service.TutoringClassService getTutoringClassService() {
        return tutoringClassService;
    }

    public void setTutoringClassService(woorinaru.core.service.TutoringClassService tutoringClassService) {
        this.tutoringClassService = tutoringClassService;
    }
}
