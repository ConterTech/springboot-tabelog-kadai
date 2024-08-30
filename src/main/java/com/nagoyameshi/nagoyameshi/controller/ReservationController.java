package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;

    // 予約一覧
    public String index(){
        return "index";
    }

    // 予約入力
    public String input(){
        return "index";
    }

    // 予約確定前確認
    public String confirm(){
        return "index";
    }
}
