package com.epam.hotelbookingspring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CustomerStartPageController {

    @GetMapping("/index")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public String getClientStartPage() {
        return "clientStartPage";
    }
}