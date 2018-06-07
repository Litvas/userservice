package com.litvas.userservice.service;

import com.litvas.userservice.domain.User;

import java.util.List;

public interface UserService {

    List<User>findUsers();

    User addUser(User user);

}
