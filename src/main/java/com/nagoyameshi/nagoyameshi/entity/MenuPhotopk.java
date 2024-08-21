package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Embeddable
@Data
public class MenuPhotopk {
    @Id
    @JoinColumn(name = "store_id")
    private Integer storeId;

    @Id
    @JoinColumn(name = "menu_id")
    private Integer menuId;
}
