package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    // ログイン
    @GetMapping("/login")
    public String login(){
        return "index";
    }

    // 新規会員登録画面表示
    @GetMapping("/signup")
    public String show(){
        return "index";
    }

    // 新規会員登録
    @PostMapping("/signup")
    public String signup(){
        return "index";
    }
}
