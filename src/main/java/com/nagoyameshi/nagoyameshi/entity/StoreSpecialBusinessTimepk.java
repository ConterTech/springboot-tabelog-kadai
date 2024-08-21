package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Embeddable
@Data
public class StoreSpecialBusinessTimepk {
    @Id
    @JoinColumn(name = "store_id")
    private Integer storeId;

    @Id
    @Column(name = "special_business_day")
    private Integer specialBusinessDay;
}
