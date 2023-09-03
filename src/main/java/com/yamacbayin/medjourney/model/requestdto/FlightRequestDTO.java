package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class FlightRequestDTO {

    private UUID airplaneUuid;

    private AddressRequestDTO departureLocation;

    private AddressRequestDTO arrivalLocation;

    private Date flightDate;

    private BigDecimal ticketPrice;

}
