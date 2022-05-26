package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.dao.dto.RoomsDto;
import com.epam.hotelbookingspring.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomServiceImpl roomService;


    @GetMapping("/all/{pageNumber}")
    @PreAuthorize("hasAuthority('rooms:read')")
    public ModelAndView getRequestsPageForUser(@PathVariable Integer pageNumber) {
        ModelAndView modelAndView = new ModelAndView("rooms");
        Page<RoomsDto> rooms = roomService.getRoomsForAdmin(pageNumber);
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

}
