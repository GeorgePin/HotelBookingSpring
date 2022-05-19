package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.service.RequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestServiceImpl requestService;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('request:create')")
    public String getRequestRoomPage() {
        return "requestRoom";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('request:create')")
    public String createRequest(@ModelAttribute("request") Request request) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Request savedRequest = requestService.createRequest(request, username);
        return "redirect:/index";
    }
}
