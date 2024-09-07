package com.crypto.dashboardweb.service.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.crypto.dashboardweb.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY_DAY = 43200;
    public static final long JWT_TOKEN_VALIDITY_MONTH = 1296000;



    @Value("${jwt.secret}")
    private String secret;

    /**
     * Retrieve subject from JWT Token
     * @param token
     * @return
     */
    public String getClaimFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Retrieve expiration date from JWT Token
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Get claim from token
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Get the role from token
     * @param token
     * @return
     */
    public String getRoleClaimFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("role");
    }

    /**
     * Get clientId from token
     * @param token
     * @return
     */
    public Long getUserIdClaimFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return Long.valueOf((Integer) claims.get("userId"));
    }

    /**
     * For retrieveing any information from token we will need the secret key
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Check if the token has expired
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generate token for user
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails, User user, boolean staySignedIn) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        return doGenerateToken(claims, userDetails.getUsername(), staySignedIn);
    }

    /**
     * While creating the token
     * 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HS512 algorithm and secret key.
     * 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1) compaction of the JWT to a URL-safe string
     *
     * @param claims
     * @param subject
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String subject, boolean staySignedIn) {
        Long validity = staySignedIn ? JWT_TOKEN_VALIDITY_MONTH : JWT_TOKEN_VALIDITY_DAY;

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Validate token
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getClaimFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
