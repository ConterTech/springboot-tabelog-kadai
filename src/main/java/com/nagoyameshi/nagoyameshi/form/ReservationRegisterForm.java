package com.nagoyameshi.nagoyameshi.form;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
    private Integer storeId;

    private Integer userId;

    private LocalDateTime checkinTime;

    private Integer numberOfPeople;

    private String remarks;
}
