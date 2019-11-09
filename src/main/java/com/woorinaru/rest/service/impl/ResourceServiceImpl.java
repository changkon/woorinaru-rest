package com.woorinaru.rest.service.impl;

import com.woorinaru.rest.dto.management.administration.Resource;
import com.woorinaru.rest.mapper.management.administration.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woorinaru.rest.service.ResourceService;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private com.woorinaru.core.service.ResourceService resourceService;

    public ResourceServiceImpl() {}

    @Override
    @Transactional
    public int create(Resource resource) {
        ResourceMapper mapper = ResourceMapper.MAPPER;
        com.woorinaru.core.model.management.administration.Resource resourceModel = mapper.mapToModel(resource);
        return this.resourceService.create(resourceModel);
    }

    @Override
    @Transactional
    public Resource get(int id) {
        com.woorinaru.core.model.management.administration.Resource resourceModel = this.resourceService.get(id);
        ResourceMapper mapper = ResourceMapper.MAPPER;
        Resource resourceDto = mapper.mapToDto(resourceModel);
        return resourceDto;
    }

    @Override
    @Transactional
    public void delete(Resource resource) {
        ResourceMapper mapper = ResourceMapper.MAPPER;
        com.woorinaru.core.model.management.administration.Resource resourceModel = mapper.mapToModel(resource);
        this.resourceService.delete(resourceModel);
    }

    @Override
    @Transactional
    public void modify(Resource resource) {
        ResourceMapper mapper = ResourceMapper.MAPPER;
        com.woorinaru.core.model.management.administration.Resource resourceModel = mapper.mapToModel(resource);
        this.resourceService.modify(resourceModel);
    }

    @Override
    @Transactional
    public List<Resource> getAll() {
        // not supported
        throw new UnsupportedOperationException("Cannot download all resource files");
    }

    public com.woorinaru.core.service.ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(com.woorinaru.core.service.ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
