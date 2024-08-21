package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.MenuPhotoRepository;

@Controller
public class MenuPhotoController {
    private final MenuPhotoRepository menuPhotoRepository;

    public MenuPhotoController(MenuPhotoRepository menuPhotoRepository){
        this.menuPhotoRepository = menuPhotoRepository;
    }
}
