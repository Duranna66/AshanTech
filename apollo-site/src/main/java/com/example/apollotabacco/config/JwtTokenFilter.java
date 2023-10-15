package com.example.apollotabacco.config;

import com.example.apollotabacco.util.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;


    public JwtTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String name = null;
        String jwt = null;
        if(header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
        }
        try{
            name = jwtTokenUtils.getUserName(jwt);
        }
        catch (ExpiredJwtException e) {
            log.debug("expired time");
        }
        catch (SignatureException e) {
            log.debug("exception with token");
        }
        if(name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    name,
                    null,
                    jwtTokenUtils.getUserRole(jwt).stream().map(SimpleGrantedAuthority::new).toList()
            );
            filterChain.doFilter(request, response);
        }
    }
}
