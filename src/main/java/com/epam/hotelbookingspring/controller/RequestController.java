package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.dao.dto.RoomsDto;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.service.RequestServiceImpl;
import com.epam.hotelbookingspring.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestServiceImpl requestService;
    private final RoomServiceImpl roomService;

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

    @GetMapping("/handle/{id}")
    @PreAuthorize("hasAuthority('request:handle')")
    public ModelAndView getPageForRequestHandling(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("requestHandling");
        Request request = requestService.getSingleRequest(id);
        modelAndView.addObject("request", request);
        Page<RoomsDto> roomsDto = roomService.getRoomsForAdminHandling(1);
        modelAndView.addObject("roomsDto", roomsDto);
        return modelAndView;
    }

    @GetMapping("/handle/{reqId}/room/{roomNumber}")
    @PreAuthorize("hasAuthority('request:handle')")
    public String handleRequest(@PathVariable Long reqId, @PathVariable Integer roomNumber) {
        requestService.handleRequest(reqId, roomNumber);
        return "redirect:/requests/all/1";
    }
}
