package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Embeddable
@Data
public class Menupk {
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

    @Id
    @JoinColumn(name = "store_id")
    @OneToMany
    private Integer storeId;
}