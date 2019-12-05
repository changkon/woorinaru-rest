package com.woorinaru.rest.security.token.identity;

import com.woorinaru.core.exception.ResourceNotFoundException;
import com.woorinaru.core.model.user.Student;
import com.woorinaru.core.model.user.User;
import com.woorinaru.core.service.StudentService;
import com.woorinaru.core.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Component
public class IdTokenUtil {

    @Autowired
    @Qualifier("proxyIdTokenVerifier")
    private IdTokenVerifier verifier;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EntityManager entityManager;

    public IdTokenUtil() {}

    @Transactional
    public User retrieveUserAndCreateIfNotExists(String token) {
        // 1. Retrieve claims from user
        Map<String, Object> claims = this.verifier.getClaims(token).orElse(null);

        if (Objects.isNull(claims)) {
            throw new IllegalArgumentException("Unknown id token received");
        }

        // 2. Retrieve user by getting the email as unique identifier
        String email = (String) claims.get(IdTokenClaimKeys.EMAIL);

        // 3. If no user exists, create if there is no existing user (always create lowest level user i.e. student)
        try {
            return this.userService.getByEmail(email);
        } catch (ResourceNotFoundException e) {
            Student studentModel = new Student();
            studentModel.setEmail(email);

            String name = (String) claims.getOrDefault(IdTokenClaimKeys.NAME, "");

            if (!StringUtils.isEmpty(name)) {
                studentModel.setName(name);
            }

            studentModel.setSignUpDateTime(LocalDateTime.now());

            int generatedId = studentService.create(studentModel);
            return studentService.get(generatedId);
        }
    }

}
