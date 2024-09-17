package com.nagoyameshi.nagoyameshi.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryEditForm {
    @NotNull
    private Integer categoryId;

    @NotEmpty(message = "カテゴリ名を入力してください。")
    private String category;
}
