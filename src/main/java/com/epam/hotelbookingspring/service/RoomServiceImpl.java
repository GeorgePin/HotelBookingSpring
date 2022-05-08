package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.RoomRepository;
import com.epam.hotelbookingspring.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Page<Room> findAtPage(int pageIndex, int rowCount, Sort.Direction direction, String field) {
        PageRequest pageRequest = PageRequest.of(pageIndex, rowCount, direction, field);
        return roomRepository.findAll(pageRequest);
    }
}
