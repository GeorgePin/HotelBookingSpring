package com.epam.hotelbookingspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class CustomerStartPageController {

    @GetMapping("/")
    public String wow() {
        return "clientStartPage";
    }
}