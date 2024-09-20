package com.nagoyameshi.nagoyameshi.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
    @NotBlank(message = "予約時間を入力してください。")
    private String checkinTimeTemp;

    @NotNull(message = "ご来店人数を入力してください。")
    @Min(value = 1, message = "人数は1人以上に設定してください。")
    private Integer numberOfPeople;

    private String remarks;

    public LocalDateTime getCheckinTime() {
        // 指定フォーマットに基づくDateTimeFormatterを作成
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return LocalDateTime.parse(checkinTimeTemp.trim(), formatter);
    }
}
