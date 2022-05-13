package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.service.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class RequestController {

    private final RequestServiceImpl requestService;

    @Autowired
    public RequestController(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/create")
    public String getRequestRoomPage() {
        return "requestRoom";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('request:create')")
    public String createRequest(@ModelAttribute("request") Request request) {
        Request savedRequest = requestService.createRequest(request);
        return "redirect:/client/index";
    }
}
