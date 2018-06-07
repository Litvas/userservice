package com.litvas.userservice.service.impl;

import com.litvas.userservice.domain.User;
import com.litvas.userservice.repository.UserRepository;
import com.litvas.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
