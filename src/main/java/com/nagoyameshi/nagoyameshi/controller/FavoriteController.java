package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;

    //お気に入り一覧表示
    @GetMapping
    public String index(){
        return "index";
    }
}
