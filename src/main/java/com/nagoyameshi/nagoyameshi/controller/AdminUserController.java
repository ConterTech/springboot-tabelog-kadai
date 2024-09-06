package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserRepository userRepository;

    // 管理者ユーザ情報一覧表示
    @GetMapping
    public String index(){
        return "index";
    }

    // 管理者ユーザ情報詳細表示
    @GetMapping("/{id}")
    public String show(){
        return "index";
    }
}
