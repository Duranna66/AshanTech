package com.example.apollotabacco.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifeTime}")
    private Duration time;
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> map = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        map.put("roles", roleList);
        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + time.toMillis());
        return Jwts.builder()
                .setClaims(map)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String getUserName(String token) {
        return getAllClaims(token).getSubject();
    }

    public List<String> getUserRoles(String token) {
        return getAllClaims(token).get("roles", List.class);
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
