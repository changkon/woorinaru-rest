package com.woorinaru.rest.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.woorinaru.core.model.management.administration.Resource;
import com.woorinaru.rest.dto.user.Admin;

@Mapper
public interface AdminMapper {

    AdminMapper MAPPER = Mappers.getMapper(AdminMapper.class);

    Admin mapToDto(com.woorinaru.core.model.user.Admin adminModel);

    com.woorinaru.core.model.user.Admin mapToModel(Admin adminDto);

    default Integer mapToDto(Resource resourceModel) {
        return resourceModel.getId();
    }

    default Resource mapToModel(Integer integer) {
        Resource resourceModel = new Resource();
        resourceModel.setId(integer);
        return resourceModel;
    }
}
