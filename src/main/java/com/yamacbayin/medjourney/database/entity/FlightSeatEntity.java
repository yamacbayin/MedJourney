package com.yamacbayin.medjourney.database.entity;

import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "flight_seats")
public class FlightSeatEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;

    private Integer seatNumber;

    private SeatStatusEnum status;

    private BigDecimal price;

    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL)
    private FlightTicketEntity ticket;

}
