package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "menu_photo")
@Data
@IdClass(value = MenuPhotopk.class)
public class MenuPhotoEntity {
    @Id
    @JoinColumn(name = "store_id")
    private Integer storeId;

    @Id
    @JoinColumn(name = "menu_id")
    @ManyToOne
    private Integer menuId;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
