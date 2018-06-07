package com.litvas.userservice.service.impl;

import com.litvas.userservice.domain.User;
import com.litvas.userservice.repository.UserRepository;
import com.litvas.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
public class UserServiceImpl implements UserService {
    private static final boolean PARALEL = false;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUsers() {
        return stream(userRepository.findAll().spliterator(), PARALEL).collect(toList());
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
