package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.RequestRepository;
import com.epam.hotelbookingspring.dao.UserRepository;
import com.epam.hotelbookingspring.dao.dto.RequestsDto;
import com.epam.hotelbookingspring.dao.impl.RequestsDtoRepositoryImpl;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl {
    private final RequestRepository requestRepository;
    private final RequestsDtoRepositoryImpl requestsDtoRepository;
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

    public List<RequestsDto> getRequestsForUser(String username) {
        Optional<User> user = userRepository.findByLogin(username);
        return requestsDtoRepository.getAllRequestsByUserId(user.get().getId());
    }

}
