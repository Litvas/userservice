package com.litvas.userservice.service.impl;

import com.litvas.userservice.domain.User;
import com.litvas.userservice.repository.UserRepository;
import com.litvas.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
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

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted!";
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
