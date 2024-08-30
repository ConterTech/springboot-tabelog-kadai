package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuRepository menuRepository;

    // 店舗詳細ページでメニュー表示
    public String index(){
        return "index";
    }

    // メニュー追加
    public String addMenu(){
        return "index";
    }

    // メニュー編集
    public String editMenu(){
        return "index";
    }

    // メニュー削除
    public String deleteMenu(){
        return "index";
    }
}
