package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Embeddable
@Data
public class Favoritepk {
    @Id
    @JoinColumn(name = "store_id")
    private Integer storeId;

    @Id
    @JoinColumn(name = "user_id")
    private Integer userId;
}
