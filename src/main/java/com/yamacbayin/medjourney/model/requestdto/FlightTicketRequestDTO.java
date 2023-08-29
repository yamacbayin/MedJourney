package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.util.UUID;

@Data
public class FlightTicketRequestDTO {

    private UUID seatUuid;

    private UUID passengerUuid;

}
