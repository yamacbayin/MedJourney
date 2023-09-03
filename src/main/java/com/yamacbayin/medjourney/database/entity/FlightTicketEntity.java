package com.yamacbayin.medjourney.database.entity;

import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flight_tickets")
public class FlightTicketEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private FlightSeatEntity seat;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private PatientEntity passenger;

}
