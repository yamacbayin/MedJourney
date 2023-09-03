package com.yamacbayin.medjourney.database.entity;

import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "flights")
public class FlightEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private AirplaneEntity airplane;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_address_id")
    private AddressEntity departureLocation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_address_id")
    private AddressEntity arrivalLocation;

    private Date flightDate;

    private BigDecimal ticketPrice;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FlightSeatEntity> seatList;

/*    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FlightTicketEntity> ticketList;*/

    @Override
    protected void prePersist() {
        super.prePersist();
        seatList = new ArrayList<>();
        for (int i = 0; i < airplane.getPassengerCapacity(); i++) {
            FlightSeatEntity seat = new FlightSeatEntity();
            seat.setStatus(SeatStatusEnum.AVAILABLE);
            seat.setFlight(this);
            seat.setSeatNumber(i + 1);
            seat.setPrice(this.ticketPrice);
            seatList.add(seat);
        }
    }

}
