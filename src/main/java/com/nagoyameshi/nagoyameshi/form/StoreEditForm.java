package com.nagoyameshi.nagoyameshi.form;

import java.time.LocalTime;

import org.springframework.web.multipart.MultipartFile;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreEditForm {
    @NotNull
    private Integer storeId;

    @NotEmpty(message = "店舗名を入力してください。")
    private String storeName;

    private MultipartFile imageFile;

    @NotEmpty(message = "郵便番号を入力してください。")
    private String postCode;

    @NotEmpty(message = "住所を入力してください。")
    private String address;

    @NotEmpty(message = "電話番号を入力してください。")
    private String phoneNumber;

    @NotNull(message = "駐車可能な台数を入力してください。")
    private Integer parkingStorage;

    @NotEmpty(message = "店舗説明を入力してください。")
    private String storeDescribe;

    @NotNull(message = "カテゴリを選択してください。")
    private CategoryEntity categoryId;

    @NotNull(message = "営業開始時間を入力してください。")
    private  LocalTime startTime;

    @NotNull(message = "営業終了時間を入力してください。")
    private LocalTime closeTime;

    private String rest;
}
