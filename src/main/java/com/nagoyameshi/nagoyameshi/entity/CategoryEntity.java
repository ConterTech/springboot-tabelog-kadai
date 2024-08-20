package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category")
    private String category;

    @Column(name = "delete_flag")
    private boolean delete_flag;
}