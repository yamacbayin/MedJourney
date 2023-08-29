package com.yamacbayin.medjourney.model.requestdto;

import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class FlightSeatRequestDTO {

    private UUID flightUuid;

    private Integer seatNumber;

    private SeatStatusEnum status;

    private BigDecimal price;

}
