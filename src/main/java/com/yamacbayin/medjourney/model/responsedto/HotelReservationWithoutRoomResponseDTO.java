package com.yamacbayin.medjourney.model.responsedto;

import lombok.Data;

import java.util.Date;

@Data
public class HotelReservationWithoutRoomResponseDTO {

    private Date checkIn;

    private Date checkOut;

    private PatientPlainResponseDTO guest;

}
