package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stripe")
@Data
public class StripeEntity {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
