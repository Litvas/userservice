package com.litvas.userservice.service;

import com.litvas.userservice.domain.User;

import java.util.List;

public interface UserService {

    List<User>findUsers();

    User addUser(User user);

    User getUser(Long id);

    String deleteUser(Long id);

    User updateUser(User user);

}
