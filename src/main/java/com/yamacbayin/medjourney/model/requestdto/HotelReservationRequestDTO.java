package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class HotelReservationRequestDTO {

    private Date checkIn;

    private Date checkOut;

    private UUID roomUuid;

    private UUID guestUuid;

}
