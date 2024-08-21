package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;

@Controller
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }
}
