package woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.management.administration.Term;
import woorinaru.rest.mapper.management.administration.TermMapper;
import woorinaru.rest.service.TermService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private woorinaru.core.service.TermService termService;

    @Override
    @Transactional
    public int create(Term term) {
        TermMapper mapper = TermMapper.MAPPER;
        woorinaru.core.model.management.administration.Term termModel = mapper.mapToModel(term);
        return this.termService.create(termModel);
    }

    @Override
    @Transactional
    public Term get(int id) {
        woorinaru.core.model.management.administration.Term termModel = this.termService.get(id);
        TermMapper mapper = TermMapper.MAPPER;
        Term termDto = mapper.mapToDto(termModel);
        return termDto;
    }

    @Override
    @Transactional
    public void delete(Term term) {
        TermMapper mapper = TermMapper.MAPPER;
        woorinaru.core.model.management.administration.Term termModel = mapper.mapToModel(term);
        this.termService.delete(termModel);
    }

    @Override
    @Transactional
    public void modify(Term term) {
        TermMapper mapper = TermMapper.MAPPER;
        woorinaru.core.model.management.administration.Term termModel = mapper.mapToModel(term);
        this.termService.modify(termModel);
    }

    @Override
    @Transactional
    public List<Term> getAll() {
        List<woorinaru.core.model.management.administration.Term> termModels = this.termService.getAll();
        TermMapper mapper = TermMapper.MAPPER;
        List<Term> termDtos = termModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return termDtos;
    }
}
