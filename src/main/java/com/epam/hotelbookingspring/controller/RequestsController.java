package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.service.RequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestsController {
    private final RequestServiceImpl requestService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('requests:readAll')")
    public ModelAndView getRequestsPageForAdmin() {
        ModelAndView modelAndView = new ModelAndView("requests");
        List<Request> requests = requestService.getRequestsForAdmin();
        modelAndView.addObject("requests", requests);
        return modelAndView;
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('requests:read')")
    public ModelAndView getRequestsPageForUser() {
        ModelAndView modelAndView = new ModelAndView("requests");
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<RequestsDto> requests = requestService.getRequestsForUser(username);
        modelAndView.addObject("requests", requests);
        return modelAndView;
    }

}
