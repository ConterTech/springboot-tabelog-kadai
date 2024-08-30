package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.MenuPhotoRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuPhotoController {
    private final MenuPhotoRepository menuPhotoRepository;

    public String i(){
        return "index";
    }
}
