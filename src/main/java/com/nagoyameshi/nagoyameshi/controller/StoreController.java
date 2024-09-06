package com.nagoyameshi.nagoyameshi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreSpecialBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.form.ReservationInputForm;
import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreBusinessTimeRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreSpecialBusinessTimeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreRepository storeRepository;
    private final StoreBusinessTimeRepository storeBusinessTimeRepository;
    private final StoreSpecialBusinessTimeRepository storeSpecialBusinessTimeRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    // 店舗詳細ページ
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "storeId") Integer storeId, Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        List<StoreBusinessTimeEntity> storeBusinessTime = storeBusinessTimeRepository.findByStoreId(storeId);
        List<StoreSpecialBusinessTimeEntity> storeSpecialBusinessTime = storeSpecialBusinessTimeRepository
                .findByStoreId(storeId);

        model.addAttribute("store", store);
        model.addAttribute("storeBusinessTime", storeBusinessTime);
        model.addAttribute("storeSpecialBusinessTime", storeSpecialBusinessTime);
        model.addAttribute("reservationForm", new ReservationInputForm());

        return "index";
    }

    // お気に入り追加
    @PostMapping("/{id}/addFavorite")
    public String addFavorite(){
        return "index";
    }

    // お気に入り削除
    @PostMapping("/{id}/deleteFavorite")
    public String deleteFavorite(){
        return "index";
    }

}
