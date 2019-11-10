package com.woorinaru.rest.security.jwt;

import com.woorinaru.core.model.role.Role;
import com.woorinaru.core.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY_MILLIS = Duration.ofMinutes(30).toMillis();

    private String secret;
    private String issuer;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.issuer}") String issuer) {
        this.secret = secret;
        this.issuer = issuer;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public String generateToken() {
        return generateToken(Optional.empty());
    }

    public String generateToken(Optional<User> user) {
        Map<String, Object> claims = createClaims(user);
        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(issuer)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_MILLIS))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public Map<String, Object> createClaims(Optional<User> user) {
        Map<String, Object> claims = new HashMap<>();

        if (user.isPresent()) {
            User userModel = user.get();
            Role role = Role.fromUser(userModel);
            claims.put(JwtCustomClaims.ROLE_KEY, role.toString());
        } else {
            claims.put(JwtCustomClaims.ROLE_KEY, Role.VISITOR.toString());
        }

        return claims;
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
