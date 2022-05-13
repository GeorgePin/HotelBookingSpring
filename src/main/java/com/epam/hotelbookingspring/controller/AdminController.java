package com.epam.hotelbookingspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/requests")
    public String getRequestsPage() {
        return "requests";
    }
}
