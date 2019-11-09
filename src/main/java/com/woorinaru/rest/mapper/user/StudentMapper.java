package com.woorinaru.rest.mapper.user;

import com.woorinaru.rest.dto.user.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.woorinaru.core.model.management.administration.Resource;

@Mapper
public interface StudentMapper {

    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    Student mapToDto(com.woorinaru.core.model.user.Student studentModel);

    com.woorinaru.core.model.user.Student mapToModel(Student studentDto);

    default Integer mapToDto(Resource resourceModel) {
        return resourceModel.getId();
    }

    default Resource mapToModel(Integer id) {
        Resource resourceModel = new Resource();
        resourceModel.setId(id);
        return resourceModel;
    }

}
