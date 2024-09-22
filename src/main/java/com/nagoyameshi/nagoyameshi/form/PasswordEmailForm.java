package com.nagoyameshi.nagoyameshi.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PasswordEmailForm {
    @NotEmpty(message = "メールアドレスを入力してください。")
    private String email;
}
