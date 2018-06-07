package com.litvas.userservice.controller;

import com.litvas.userservice.domain.User;
import com.litvas.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
