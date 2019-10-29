package woorinaru.rest.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import woorinaru.rest.dto.user.Admin;
import woorinaru.rest.mapper.management.administration.ResourceMapper;

@Mapper(uses={ResourceMapper.class})
public interface AdminMapper {

    AdminMapper MAPPER = Mappers.getMapper(AdminMapper.class);

    Admin mapToDto(woorinaru.core.model.user.Admin adminModel);

    woorinaru.core.model.user.Admin mapToModel(Admin adminDto);

}
