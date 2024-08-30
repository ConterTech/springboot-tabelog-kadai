package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // ユーザ情報表示
    public String index(){
        return "index";
    }
    
    // 編集画面表示
    public String edit(){
        return "index";
    }

    // 編集
    public String update(){
        return "index";
    }

    // 管理者ユーザ情報一覧表示
    public String indexAdmin(){
        return "index";
    }

    // 管理者ユーザ情報詳細表示
    public String showAdmin(){
        return "index";
    }
}
