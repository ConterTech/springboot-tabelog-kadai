package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;

    // レビュー一覧表示
    public String index(){
        return "index";
    }

    // レビュー投稿画面表示
    public String register(){
        return "index";
    }

    // レビュー投稿
    public String create(){
        return "index";
    }

    // レビュー編集画面表示
    public String edit(){
        return "index";
    }

    // レビュー編集
    public String update(){
        return "index";
    }

    // レビュー削除
    public String delete(){
        return "index";
    }
}
