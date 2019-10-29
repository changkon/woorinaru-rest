package woorinaru.rest.mapper.management.administration;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import woorinaru.rest.dto.management.administration.Resource;

@Mapper
public interface ResourceMapper {

    ResourceMapper MAPPER = Mappers.getMapper(ResourceMapper.class);

    Resource mapToDto(woorinaru.core.model.management.administration.Resource resourceModel);

    woorinaru.core.model.management.administration.Resource mapToModel(Resource resourceDto);

}
