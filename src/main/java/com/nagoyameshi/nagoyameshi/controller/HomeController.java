package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //homeページ
    @GetMapping("/")
    public String index(){
        return "index";
    }
}



