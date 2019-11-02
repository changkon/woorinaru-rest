package woorinaru.rest.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;
import woorinaru.core.model.management.administration.Resource;
import woorinaru.rest.dto.management.administration.Team;
import woorinaru.rest.dto.user.Staff;
import woorinaru.rest.dto.user.StaffRole;

@Mapper
public interface StaffMapper {

    StaffMapper MAPPER = Mappers.getMapper(StaffMapper.class);

    Staff mapToDto(woorinaru.core.model.user.Staff staffModel);

    woorinaru.core.model.user.Staff mapToModel(Staff staffDto);

    default Integer mapToDto(Resource resourceModel) {
        return resourceModel.getId();
    }

    default Resource mapToModel(Integer integer) {
        Resource resourceModel = new Resource();
        resourceModel.setId(integer);
        return resourceModel;
    }

    @ValueMappings({
        @ValueMapping(source="LEADER", target="LEADER"),
        @ValueMapping(source="VICE_LEADER", target="VICE_LEADER"),
        @ValueMapping(source="SUB_LEADER", target="SUB_LEADER"),
        @ValueMapping(source="TEACHER", target="TEACHER")
    })
    StaffRole mapStaffRoleModelToStaffRoleDto(woorinaru.core.model.user.StaffRole staffRoleModel);

    @ValueMappings({
        @ValueMapping(source="LEADER", target="LEADER"),
        @ValueMapping(source="VICE_LEADER", target="VICE_LEADER"),
        @ValueMapping(source="SUB_LEADER", target="SUB_LEADER"),
        @ValueMapping(source="TEACHER", target="TEACHER")
    })
    woorinaru.core.model.user.StaffRole mapStaffRoleDtoToStaffRoleModel(StaffRole staffRoleDto);

    @ValueMappings({
        @ValueMapping(source="PLANNING", target="PLANNING"),
        @ValueMapping(source="DESIGN", target="DESIGN"),
        @ValueMapping(source="MEDIA", target="MEDIA"),
        @ValueMapping(source="EDUCATION", target="EDUCATION")
    })
    Team mapTeamModelToTeamDto(woorinaru.core.model.management.administration.Team teamModel);

    @ValueMappings({
        @ValueMapping(source="PLANNING", target="PLANNING"),
        @ValueMapping(source="DESIGN", target="DESIGN"),
        @ValueMapping(source="MEDIA", target="MEDIA"),
        @ValueMapping(source="EDUCATION", target="EDUCATION")
    })
    woorinaru.core.model.management.administration.Team mapTeamDtoToTeamModel(Team teamDto);

}
