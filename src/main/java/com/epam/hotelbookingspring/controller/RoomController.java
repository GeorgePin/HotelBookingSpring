package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;


    @GetMapping("/edit/block/{roomId}")
    @PreAuthorize("hasAuthority('room:edit')")
    public String blockRoom(@PathVariable Long roomId) {
        roomService.blockRoom(roomId);
        return "redirect:/rooms/all/1";
    }

    @GetMapping("/edit/unblock/{roomId}")
    @PreAuthorize("hasAuthority('room:edit')")
    public String unblockRoom(@PathVariable Long roomId) {
        roomService.unblockRoom(roomId);
        return "redirect:/rooms/all/1";
    }

    @GetMapping("/delete/{roomId}")
    @PreAuthorize("hasAuthority('room:delete')")
    public String deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return "redirect:/rooms/all/1";
    }
}
