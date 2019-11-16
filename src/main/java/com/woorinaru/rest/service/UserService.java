package com.woorinaru.rest.service;

import com.woorinaru.rest.dto.user.User;

public interface UserService {
    User get(int id);
    User getByEmail(String email);
    void modifyUserType(User user, Class<? extends User> userType);
}
