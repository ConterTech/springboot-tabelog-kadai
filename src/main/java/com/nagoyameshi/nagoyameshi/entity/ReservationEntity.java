package com.nagoyameshi.nagoyameshi.entity;

import java.sql.Time;

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
@Table(name = "reservation")
@Data
@IdClass(value = Reservationpk.class)
public class ReservationEntity {
    @Id
    @JoinColumn(name = "store_id")
    @OneToMany
    private Integer storeId;

    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Integer userId;

    @Column(name = "checkin_time")
    private Time checkinTime;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}