package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.StoreSpecialBusinessTimeRepository;

public class StoreSpecialBusinessTimeController {
    private final StoreSpecialBusinessTimeRepository specialBusinessTimeRepository;

    public StoreSpecialBusinessTimeController(StoreSpecialBusinessTimeRepository storeSpecialBusinessTimeRepository){
        this.specialBusinessTimeRepository = storeSpecialBusinessTimeRepository;
    }
}
