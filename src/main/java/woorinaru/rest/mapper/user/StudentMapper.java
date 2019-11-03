package woorinaru.rest.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import woorinaru.core.model.management.administration.Resource;
import woorinaru.rest.dto.user.Student;

@Mapper
public interface StudentMapper {

    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    Student mapToDto(woorinaru.core.model.user.Student studentModel);

    woorinaru.core.model.user.Student mapToModel(Student studentDto);

    default Integer mapToDto(Resource resourceModel) {
        return resourceModel.getId();
    }

    default Resource mapToModel(Integer id) {
        Resource resourceModel = new Resource();
        resourceModel.setId(id);
        return resourceModel;
    }

}
