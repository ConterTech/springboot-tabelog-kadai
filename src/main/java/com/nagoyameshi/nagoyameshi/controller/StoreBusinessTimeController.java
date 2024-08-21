package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.StoreBusinessTimeRepository;

@Controller
public class StoreBusinessTimeController {
    private final StoreBusinessTimeRepository storeBusinessTimeRepository;

    public StoreBusinessTimeController(StoreBusinessTimeRepository storeBusinessTimeRepository){
        this.storeBusinessTimeRepository = storeBusinessTimeRepository;
    }
}
