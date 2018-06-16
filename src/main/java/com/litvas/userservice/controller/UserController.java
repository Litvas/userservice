package com.litvas.userservice.controller;

import com.litvas.userservice.domain.User;
import com.litvas.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @PostMapping("add_user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}