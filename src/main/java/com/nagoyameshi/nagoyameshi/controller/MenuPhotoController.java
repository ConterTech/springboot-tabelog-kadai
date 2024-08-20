package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.MenuPhotoRepository;

public class MenuPhotoController {
    private final MenuPhotoRepository menuPhotoRepository;

    public MenuPhotoController(MenuPhotoRepository menuPhotoRepository){
        this.menuPhotoRepository = menuPhotoRepository;
    }
}
