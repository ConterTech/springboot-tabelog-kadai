package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/houses")
public class ReviewController {
    private final ReviewRepository reviewRepository;

    // レビュー一覧表示
    @GetMapping("{id}/review")
    public String index(){
        return "index";
    }

    // レビュー投稿画面表示
    @GetMapping("{id}/register")
    public String register(){
        return "index";
    }

    // レビュー投稿
    @PostMapping("{id}/create")
    public String create(){
        return "index";
    }

    // レビュー編集画面表示
    @GetMapping("{id}/edit")
    public String edit(){
        return "index";
    }

    // レビュー編集
    @PostMapping("{id}/update")
    public String update(){
        return "index";
    }

    // レビュー削除
    @PostMapping("{id}/delete")
    public String delete(){
        return "index";
    }
}
