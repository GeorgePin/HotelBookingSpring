package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.RequestRepository;
import com.epam.hotelbookingspring.dao.RequestsDtoRepository;
import com.epam.hotelbookingspring.dao.RoomRepository;
import com.epam.hotelbookingspring.dao.UserRepository;
import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.Room;
import com.epam.hotelbookingspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl {

    private static final int ITEMS_PER_PAGE = 5;
    private final RequestRepository requestRepository;
    private final RoomRepository roomRepository;
    private final RequestsDtoRepository requestsDtoRepository;
    private final UserRepository userRepository;

    public Request createRequest(Request request, String username) {
        Optional<User> user = userRepository.findByLogin(username);
        request.setUserId(user.get().getId());
        Request savedRequest = requestRepository.save(request);
        return savedRequest;
    }

    public List<Request> getRequestsForAdmin() {
        return requestRepository.getRequestsForAdmin();
    }

    public Page<RequestsDto> getRequestsForUser(String username, Integer pageNumber) {
        Optional<User> user = userRepository.findByLogin(username);
        Pageable paging = PageRequest.of(pageNumber - 1, ITEMS_PER_PAGE);
        return requestsDtoRepository.getAllRequestsByUserId(user.get().getId(), paging);
    }

    public Request getSingleRequest(Long requestId) {
        return requestRepository.findRequestById(requestId);
    }

    @Transactional
    public void handleRequest(Long reqId, Integer roomNumber) {
        Room room = roomRepository.findRoomByNumber(roomNumber);
        Long roomId = room.getId();
        roomRepository.blockRoom(roomId);
        requestRepository.handleRequest(reqId, roomId);
    }
}
