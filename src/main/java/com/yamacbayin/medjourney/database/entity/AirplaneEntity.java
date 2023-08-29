package com.yamacbayin.medjourney.database.entity;

import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "airplanes")
public class AirplaneEntity extends BaseEntity {

    private String manufacturer;

    private String model;

    private Integer passengerCapacity;

    @Column(unique = true)
    private String registrationId;

}
