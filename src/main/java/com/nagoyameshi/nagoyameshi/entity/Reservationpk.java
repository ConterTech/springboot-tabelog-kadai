package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Embeddable
@Data
public class Reservationpk {
    @Id
    @JoinColumn(name = "store_id")
    @OneToMany
    private Integer storeId;

    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Integer userId;
}
