package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.RequestRepository;
import com.epam.hotelbookingspring.dao.RequestsDtoRepository;
import com.epam.hotelbookingspring.dao.UserRepository;
import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.dao.impl.RequestsDtoRepositoryImpl;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl {

    private static final int ITEMS_PER_PAGE = 5;
    private final RequestRepository requestRepository;
    private final RequestsDtoRepository requestsDtoRepository;
    private final UserRepository userRepository;

    public Request createRequest(Request request, String username) {
        Optional<User> user = userRepository.findByLogin(username);
        request.setUserId(user.get().getId());
        Request savedRequest = requestRepository.save(request);
        return savedRequest;
    }

    public List<Request> getRequestsForAdmin() {
        return requestRepository.findAll();
    }

    public Page<RequestsDto> getRequestsForUser(String username, Integer pageNumber) {
        Integer startElement = (pageNumber - 1) * ITEMS_PER_PAGE;
        Optional<User> user = userRepository.findByLogin(username);
        Pageable paging = PageRequest.of(startElement, ITEMS_PER_PAGE);
        return requestsDtoRepository.getAllRequestsByUserId(user.get().getId(), paging);
    }

}
