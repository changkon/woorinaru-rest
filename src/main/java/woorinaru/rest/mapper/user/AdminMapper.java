package woorinaru.rest.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import woorinaru.core.model.management.administration.Resource;
import woorinaru.rest.dto.user.Admin;
import woorinaru.rest.mapper.management.administration.ResourceMapper;

@Mapper(uses={ResourceMapper.class})
public interface AdminMapper {

    AdminMapper MAPPER = Mappers.getMapper(AdminMapper.class);

    Admin mapToDto(woorinaru.core.model.user.Admin adminModel);

    woorinaru.core.model.user.Admin mapToModel(Admin adminDto);

    default Integer mapToDto(Resource resourceModel) {
        return resourceModel.getId();
    }

    default Resource mapToModel(Integer integer) {
        Resource resourceModel = new Resource();
        resourceModel.setId(integer);
        return resourceModel;
    }
}
