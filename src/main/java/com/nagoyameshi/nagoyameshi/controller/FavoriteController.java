package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;

    //お気に入り一覧表示
    public String index(){
        return "index";
    }

    //お気に入り追加
    public String addFavorite(){
        return "index";
    }

    //お気に入り削除
    public String deleteFavorite(){
        return "index";
    }
}
