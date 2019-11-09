package com.woorinaru.rest.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;
import com.woorinaru.core.model.management.administration.Resource;
import com.woorinaru.rest.dto.management.administration.Team;
import com.woorinaru.rest.dto.user.Staff;
import com.woorinaru.rest.dto.user.StaffRole;

@Mapper
public interface StaffMapper {

    StaffMapper MAPPER = Mappers.getMapper(StaffMapper.class);

    Staff mapToDto(com.woorinaru.core.model.user.Staff staffModel);

    com.woorinaru.core.model.user.Staff mapToModel(Staff staffDto);

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
    StaffRole mapStaffRoleModelToStaffRoleDto(com.woorinaru.core.model.user.StaffRole staffRoleModel);

    @ValueMappings({
        @ValueMapping(source="LEADER", target="LEADER"),
        @ValueMapping(source="VICE_LEADER", target="VICE_LEADER"),
        @ValueMapping(source="SUB_LEADER", target="SUB_LEADER"),
        @ValueMapping(source="TEACHER", target="TEACHER")
    })
    com.woorinaru.core.model.user.StaffRole mapStaffRoleDtoToStaffRoleModel(StaffRole staffRoleDto);

    @ValueMappings({
        @ValueMapping(source="PLANNING", target="PLANNING"),
        @ValueMapping(source="DESIGN", target="DESIGN"),
        @ValueMapping(source="MEDIA", target="MEDIA"),
        @ValueMapping(source="EDUCATION", target="EDUCATION")
    })
    Team mapTeamModelToTeamDto(com.woorinaru.core.model.management.administration.Team teamModel);

    @ValueMappings({
        @ValueMapping(source="PLANNING", target="PLANNING"),
        @ValueMapping(source="DESIGN", target="DESIGN"),
        @ValueMapping(source="MEDIA", target="MEDIA"),
        @ValueMapping(source="EDUCATION", target="EDUCATION")
    })
    com.woorinaru.core.model.management.administration.Team mapTeamDtoToTeamModel(Team teamDto);

}
