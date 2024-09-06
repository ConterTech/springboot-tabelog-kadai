package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // ユーザ情報表示
    @GetMapping
    public String index(){
        return "index";
    }
    
    // 編集画面表示
    @GetMapping("/edit")
    public String edit(){
        return "index";
    }

    // 編集
    @PostMapping("/update")
    public String update(){
        return "index";
    }
}
