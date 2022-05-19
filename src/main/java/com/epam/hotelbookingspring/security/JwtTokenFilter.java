package com.epam.hotelbookingspring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenFilter extends GenericFilterBean {
    private AuthenticationManager authenticationManager;
    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (requestUri.endsWith(".css") || requestUri.endsWith(".png") || requestUri.endsWith(".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Cookie cookie = WebUtils.getCookie((HttpServletRequest) servletRequest, "accessToken");
        if (cookie == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Jws<Claims> claimsJws = Jwts
                .parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(cookie.getValue());
        boolean hasTokenExpired = claimsJws.getBody().getExpiration().before(new Date(System.currentTimeMillis()));
        try {
            if (!hasTokenExpired) {
                List<String> authorities = (List<String>) claimsJws.getBody().get("roles");
                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority))
                        .collect(Collectors.toSet());
                Authentication authentication = new UsernamePasswordAuthenticationToken(claimsJws.getBody().getSubject(), null, simpleGrantedAuthorities);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception exception) {
            throw exception;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

}