package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.UserRepository;
import com.epam.hotelbookingspring.model.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> login(User user) throws NoSuchAlgorithmException {
        return userRepository.findByLoginAndPassword(user.getLogin(), getMD5(user.getPassword()));
    }

    public static String getMD5(String data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(data));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}
