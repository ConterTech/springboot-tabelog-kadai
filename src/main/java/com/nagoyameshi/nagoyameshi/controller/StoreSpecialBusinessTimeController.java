package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.StoreSpecialBusinessTimeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreSpecialBusinessTimeController {
    private final StoreSpecialBusinessTimeRepository specialBusinessTimeRepository;

    // 店舗詳細ページ
    public String show(){
        return "index";
    }

    // 登録画面表示
    public String register(){
        return "index";
    }

    // 登録
    public String create(){
        return "index";
    }
    // 編集画面表示
    public String edit(){
        return "index";
    }

    // 編集
    public String update(){
        return "index";
    }

    // 削除
    public String delete(){
        return "index";
    }
}
