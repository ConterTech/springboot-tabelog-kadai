package com.nagoyameshi.nagoyameshi.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryRegisterForm {
    private Integer categoryId;

    @NotEmpty(message = "カテゴリ名を入力してください。")
    private String category;
}