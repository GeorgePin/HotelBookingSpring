package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.RequestRepository;
import com.epam.hotelbookingspring.dao.UserRepository;
import com.epam.hotelbookingspring.model.Request;
import com.epam.hotelbookingspring.model.Room;
import com.epam.hotelbookingspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestServiceImpl {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Request createRequest(Request request) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByLogin(username);
        request.setUserId(user.get().getId());
        Request savedRequest = requestRepository.save(request);
        return savedRequest;
    }
}
