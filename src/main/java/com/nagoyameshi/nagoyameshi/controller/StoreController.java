package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    // 店舗詳細ページ
    @GetMapping("/{id}")
    public String show(){
        return "index";
    }

    // 管理者店舗一覧表示
    public String indexAdmin(){
        return "index";
    }

    // 管理者店舗登録画面表示
    public String register(){
        return "index";
    }

    // 管理者店舗登録
    public String create(){
        return "index";
    }

    // 管理者店舗編集画面
    public String edit(){
        return "index";
    }

    // 管理者店舗編集
    public String update(){
        return "index";
    }

    // 管理者店舗削除
    public String delete(){
        return "index";
    }

}
