package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "address")
    private String address;

    @Column(name = "mail_address")
    private String mailAddress;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "password")
    private String pasword;

    @Column(name = "admin_flag")
    private boolean adminFlag;

    @Column(name = "paid_flag")
    private boolean paidFlag;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
