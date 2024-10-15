package com.nissum.user.infrastructure.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "8a71a6fd95cf3c42d1777e4bef1d61d740dd4c483230e1e9b7405258166d5e94";
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();
        SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 10 hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
