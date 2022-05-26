package com.epam.hotelbookingspring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        log.info("AuthenticationFilter has been invoked, attempting authentication");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        return super.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws AuthenticationException, IOException {
        User user = (User) authentication.getPrincipal();
        List<String> permissions = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        Date date = new Date(System.currentTimeMillis() + validityInMilliseconds);
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", permissions)
                .sign(algorithm);
        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .maxAge(date.getTime()).httpOnly(true).path("/").build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        for (String permission : permissions) {
            if (permission.equals("ROLE_ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/rooms/all/1");
                return;
            } else if (permission.equals("ROLE_CLIENT")) {
                response.sendRedirect(request.getContextPath() + "/index");
                return;
            }
        }
        throw new RuntimeException("Unknown role");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}