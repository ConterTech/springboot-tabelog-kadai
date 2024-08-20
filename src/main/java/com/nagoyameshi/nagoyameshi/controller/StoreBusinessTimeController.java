package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.StoreBusinessTimeRepository;

public class StoreBusinessTimeController {
    private final StoreBusinessTimeRepository storeBusinessTimeRepository;

    public StoreBusinessTimeController(StoreBusinessTimeRepository storeBusinessTimeRepository){
        this.storeBusinessTimeRepository = storeBusinessTimeRepository;
    }
}
