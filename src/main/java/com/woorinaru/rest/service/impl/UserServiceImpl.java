package com.woorinaru.rest.service.impl;

import com.woorinaru.rest.dto.user.Admin;
import com.woorinaru.rest.dto.user.Staff;
import com.woorinaru.rest.dto.user.Student;
import com.woorinaru.rest.dto.user.User;
import com.woorinaru.rest.mapper.user.UserMapper;
import com.woorinaru.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private com.woorinaru.core.service.UserService userService;

    @Override
    @Transactional
    public User get(int id) {
        com.woorinaru.core.model.user.User userModel = this.userService.get(id);
        UserMapper mapper = UserMapper.MAPPER;
        User userDto = mapper.mapToDto(userModel);
        return userDto;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        com.woorinaru.core.model.user.User userModel = this.userService.getByEmail(email);
        UserMapper mapper = UserMapper.MAPPER;
        User userDto = mapper.mapToDto(userModel);
        return userDto;
    }

    @Override
    @Transactional
    public void modifyUserType(User user, Class<? extends User> userType) {
        UserMapper mapper = UserMapper.MAPPER;
        com.woorinaru.core.model.user.User userModel = mapper.mapToModel(user);

        Class<? extends com.woorinaru.core.model.user.User> type = getUserType(userType);

        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("Invalid user type");
        }

        this.userService.modifyUserType(userModel, type);
    }

    private Class<? extends com.woorinaru.core.model.user.User> getUserType(Class<? extends User> userType) {
        if (userType == Admin.class) {
            return com.woorinaru.core.model.user.Admin.class;
        } else if (userType == Staff.class) {
            return com.woorinaru.core.model.user.Staff.class;
        } else if (userType == Student.class) {
            return com.woorinaru.core.model.user.Student.class;
        }

        return null;
    }
}
