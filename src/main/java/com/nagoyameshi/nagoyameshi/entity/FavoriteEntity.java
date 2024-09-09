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
@Table(name = "favorite")
@Data
@IdClass(value = Favoritepk.class)
public class FavoriteEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "delete_flag")
    private boolean deleteFlag; 
}
