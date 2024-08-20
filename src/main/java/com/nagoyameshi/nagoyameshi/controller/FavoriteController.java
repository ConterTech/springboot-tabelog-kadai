package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;

public class FavoriteController {
    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }
}
