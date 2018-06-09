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

import java.util.Arrays;
import java.util.List;

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

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
