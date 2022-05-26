package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.RoomRepository;
import com.epam.hotelbookingspring.dao.RoomsDtoRepository;
import com.epam.hotelbookingspring.dao.dto.RoomsDto;
import com.epam.hotelbookingspring.model.Room;
import com.epam.hotelbookingspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl {

    private static final int ITEMS_PER_PAGE = 5;
    private final RoomsDtoRepository roomsDtoRepository;
    private final RoomRepository roomRepository;

    public Page<RoomsDto> getRoomsForAdmin(Integer pageNumber) {
        Pageable paging = PageRequest.of(pageNumber - 1, ITEMS_PER_PAGE);
        return roomsDtoRepository.getRoomsWithPrices(paging);
    }

    public Page<RoomsDto> getRoomsForAdminHandling(Integer pageNumber) {
        Pageable paging = PageRequest.of(pageNumber - 1, ITEMS_PER_PAGE);
        return roomsDtoRepository.getRoomsWithPricesAndNonBlocked(paging);
    }

    @Transactional
    public void blockRoom(Long roomId) {
        roomRepository.blockRoom(roomId);
    }

    @Transactional
    public void unblockRoom(Long roomId) {
        roomRepository.unblockRoom(roomId);
    }

    @Transactional
    public void deleteRoom(Long roomId) {
        roomRepository.deleteRoom(roomId);
    }
}
