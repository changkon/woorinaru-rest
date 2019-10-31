package woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.management.administration.Resource;
import woorinaru.rest.mapper.management.administration.ResourceMapper;
import woorinaru.rest.service.ResourceService;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private woorinaru.core.service.ResourceService resourceService;

    public ResourceServiceImpl() {}

    @Override
    @Transactional
    public int create(Resource resource) {
        ResourceMapper mapper = ResourceMapper.MAPPER;
        woorinaru.core.model.management.administration.Resource resourceModel = mapper.mapToModel(resource);
        return this.resourceService.create(resourceModel);
    }

    @Override
    public Resource get(int id) {
        woorinaru.core.model.management.administration.Resource resourceModel = this.resourceService.get(id);
        ResourceMapper mapper = ResourceMapper.MAPPER;
        Resource resourceDto = mapper.mapToDto(resourceModel);
        return resourceDto;
    }

    @Override
    public void delete(Resource resource) {
        ResourceMapper mapper = ResourceMapper.MAPPER;
        woorinaru.core.model.management.administration.Resource resourceModel = mapper.mapToModel(resource);
        this.resourceService.delete(resourceModel);
    }

    @Override
    public void modify(Resource resource) {
        ResourceMapper mapper = ResourceMapper.MAPPER;
        woorinaru.core.model.management.administration.Resource resourceModel = mapper.mapToModel(resource);
        this.resourceService.modify(resourceModel);
    }

    @Override
    public List<Resource> getAll() {
        // not supported
        throw new UnsupportedOperationException("Cannot download all resource files");
    }

    public woorinaru.core.service.ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(woorinaru.core.service.ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
