package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.StoreSpecialBusinessTimeRepository;

@Controller
public class StoreSpecialBusinessTimeController {
    private final StoreSpecialBusinessTimeRepository specialBusinessTimeRepository;

    public StoreSpecialBusinessTimeController(StoreSpecialBusinessTimeRepository storeSpecialBusinessTimeRepository){
        this.specialBusinessTimeRepository = storeSpecialBusinessTimeRepository;
    }
}
