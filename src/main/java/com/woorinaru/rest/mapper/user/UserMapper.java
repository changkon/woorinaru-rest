package com.woorinaru.rest.mapper.user;

import com.woorinaru.rest.dto.user.Admin;
import com.woorinaru.rest.dto.user.Staff;
import com.woorinaru.rest.dto.user.Student;
import com.woorinaru.rest.dto.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    default User mapToDto(com.woorinaru.core.model.user.User userModel) {
        if (userModel instanceof com.woorinaru.core.model.user.Admin) {
            return AdminMapper.MAPPER.mapToDto((com.woorinaru.core.model.user.Admin) userModel);
        } else if (userModel instanceof com.woorinaru.core.model.user.Staff) {
            return StaffMapper.MAPPER.mapToDto((com.woorinaru.core.model.user.Staff) userModel);
        } else if (userModel instanceof com.woorinaru.core.model.user.Student) {
            return StudentMapper.MAPPER.mapToDto((com.woorinaru.core.model.user.Student) userModel);
        }

        return null;
    }

    default com.woorinaru.core.model.user.User mapToModel(User userDto) {
        if (userDto instanceof Admin) {
            return AdminMapper.MAPPER.mapToModel((Admin) userDto);
        } else if (userDto instanceof Staff) {
            return StaffMapper.MAPPER.mapToModel((Staff) userDto);
        } else if (userDto instanceof Student) {
            return StudentMapper.MAPPER.mapToModel((Student) userDto);
        }

        return null;
    }

}
