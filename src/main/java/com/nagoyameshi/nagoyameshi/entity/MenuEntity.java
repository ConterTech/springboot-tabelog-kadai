package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "menu")
@Data
@IdClass(value = Menupk.class)
public class MenuEntity {
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

    @Id
    @JoinColumn(name = "store_id")
    private Integer storeId;

    @Column(name = "menu")
    private String menu;

    @Column(name = "price")
    private Integer price;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
