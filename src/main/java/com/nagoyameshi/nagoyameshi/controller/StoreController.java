package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.StoreRepository;

@Controller
public class StoreController {
    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
}
