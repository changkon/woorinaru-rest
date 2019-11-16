package com.woorinaru.rest.security.jwt;

import com.woorinaru.core.model.security.Role;
import com.woorinaru.core.model.user.Admin;
import com.woorinaru.core.model.user.Staff;
import com.woorinaru.core.model.user.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenUtilTest {

    private static final String ISSUER = "woorinaru";
    private static final String SECRET = "woorinaru";

    @Test
    public void testGenerateTokenWithNoUserContainsVisitorRole() {
        // GIVEN
        JwtTokenUtil util = new JwtTokenUtil(SECRET, ISSUER);

        // WHEN
        String token = util.generateToken();

        // THEN
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();

        assertThat(claims.getIssuer()).isEqualTo(ISSUER);
        assertThat(claims.get(JwtCustomClaims.ROLE_KEY, String.class)).isEqualTo(Role.VISITOR.toString());
    }

    @Test
    public void testGenerateTokenWithAdminContainsAdminRole() {
        // GIVEN
        JwtTokenUtil util = new JwtTokenUtil(SECRET, ISSUER);
        Admin admin = new Admin();

        // WHEN
        String token = util.generateToken(Optional.of(admin));

        // THEN
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();

        assertThat(claims.getIssuer()).isEqualTo(ISSUER);
        assertThat(claims.get(JwtCustomClaims.ROLE_KEY, String.class)).isEqualTo(Role.ADMIN.toString());
    }

    @Test
    public void testGenerateTokenWithStaffContainsStaffRole() {
        // GIVEN
        JwtTokenUtil util = new JwtTokenUtil(SECRET, ISSUER);
        Staff staff = new Staff();

        // WHEN
        String token = util.generateToken(Optional.of(staff));

        // THEN
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();

        assertThat(claims.getIssuer()).isEqualTo(ISSUER);
        assertThat(claims.get(JwtCustomClaims.ROLE_KEY, String.class)).isEqualTo(Role.TEACHER.toString());
    }

    @Test
    public void testGenerateTokenWithStudentContainsStudentRole() {
        // GIVEN
        JwtTokenUtil util = new JwtTokenUtil(SECRET, ISSUER);
        Student student = new Student();

        // WHEN
        String token = util.generateToken(Optional.of(student));

        // THEN
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();

        assertThat(claims.getIssuer()).isEqualTo(ISSUER);
        assertThat(claims.get(JwtCustomClaims.ROLE_KEY, String.class)).isEqualTo(Role.STUDENT.toString());
    }

    @Test
    public void testValidateTokenReturnsFalseWhenExpired() {
        // GIVEN
        long currentTimeMillis = System.currentTimeMillis();
        long expiryTimeMillis = currentTimeMillis - Duration.ofMinutes(1).toMillis();

        String token = Jwts.builder()
            .setIssuer(ISSUER)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(expiryTimeMillis))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
        JwtTokenUtil util = new JwtTokenUtil(SECRET, ISSUER);

        // WHEN
        boolean valid = util.validateToken(token);

        // THEN
        assertThat(valid).isFalse();
    }

    @Test
    public void testValidateTokenReturnsTrueWhenNotExpired() {
        // GIVEN
        long currentTimeMillis = System.currentTimeMillis();
        long expiryTimeMillis = currentTimeMillis + Duration.ofMinutes(1).toMillis();
        String token = Jwts.builder()
            .setIssuer(ISSUER)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(expiryTimeMillis))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
        JwtTokenUtil util = new JwtTokenUtil(SECRET, ISSUER);

        // WHEN
        boolean valid = util.validateToken(token);

        // THEN
        assertThat(valid).isTrue();
    }
}
