package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
@IdClass(value = Reviewpk.class)
public class ReviewEntity {
    @Id
    @JoinColumn(name = "store_id")
    @OneToMany
    private Integer storeId;

    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Integer userId;

    @Column(name = "review_star")
    private Integer reviewStar;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
