package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.StoreRepository;

public class StoreController {
    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
}
