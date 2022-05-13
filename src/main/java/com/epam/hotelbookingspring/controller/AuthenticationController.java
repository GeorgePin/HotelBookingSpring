package com.epam.hotelbookingspring.controller;


import com.epam.hotelbookingspring.dao.dto.AuthenticationRequestDTO;
import com.epam.hotelbookingspring.jwt.JwtTokenProvider;
import com.epam.hotelbookingspring.model.security.Role;
import com.epam.hotelbookingspring.model.User;
import com.epam.hotelbookingspring.service.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, UserServiceImpl userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public String authenticate(@ModelAttribute AuthenticationRequestDTO authenticationDTO, HttpServletResponse response) throws NoSuchAlgorithmException {
        try {
            String login = authenticationDTO.getLogin();
            String password = authenticationDTO.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            Optional<User> user = userService.loadUserByUsername(login);
            String token = jwtTokenProvider.createToken(authenticationDTO.getLogin(), user.get().getRole().name());
            response.setHeader(HttpHeaders.AUTHORIZATION, token);
            return user.get().getRole().equals(Role.ADMIN) ? "redirect:/admin/requests" : "redirect:/client/index";
        } catch (AuthenticationException exception) {
            //TODO 1. incorrect login. 2. incorrect password. 3. You are banned
            return "index";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return "redirect:/";
    }
}