package com.woorinaru.rest.security.authentication;

import com.woorinaru.core.model.user.Student;
import com.woorinaru.core.model.user.User;
import com.woorinaru.core.service.AdminService;
import com.woorinaru.core.service.StaffService;
import com.woorinaru.core.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class IdTokenUtil {

    @Autowired
    @Qualifier("proxyIdTokenVerifier")
    private IdTokenVerifier verifier;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StaffService staffService;

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
        Query query = entityManager.createQuery("SELECT u.ID, u.USER_TYPE FROM User u WHERE u.email=:email")
            .setParameter("email", email);
        List<Object[]> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            Student studentModel = new Student();
            int generatedId = studentService.create(studentModel);
            return studentService.get(generatedId);
        } else {
            Object[] queryResult = resultList.get(0);
            // Provide defaults
            int userId = (int) queryResult[0];
            char userType = (char) queryResult[1];

            // 4. Return user
            switch (userType) {
                case 'A':
                    return adminService.get(userId);
                case 'T':
                    return staffService.get(userId);
                case 'S':
                default:
                    return studentService.get(userId);
            }
        }
    }

}
