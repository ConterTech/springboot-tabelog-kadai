package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.UserRepository;

public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
