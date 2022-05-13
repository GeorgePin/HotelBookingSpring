package com.epam.hotelbookingspring.service;

import com.epam.hotelbookingspring.dao.UserRepository;
import com.epam.hotelbookingspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> loadUserByUsername(String login) throws NoSuchAlgorithmException {
        return userRepository.findByLogin(login);
    }

//    public static String getMD5(String data) throws NoSuchAlgorithmException {
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            md5.update(StandardCharsets.UTF_8.encode(data));
//            return String.format("%032x", new BigInteger(1, md5.digest()));
//        } catch (NoSuchAlgorithmException exception) {
//            throw new NoSuchAlgorithmException("Algorithm wasn't found");
//        }
//    }
}
