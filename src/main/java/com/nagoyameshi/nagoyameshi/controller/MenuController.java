package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.MenuRepository;

@Controller
public class MenuController {
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }
}
