package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class HotelReservationResponseDTO extends BaseResponseDTO {

    private Date checkIn;

    private Date checkOut;

    private HotelPlainResponseDTO hotel;

    private HotelRoomPlainResponseDTO room;

    private PatientPlainResponseDTO guest;

}
