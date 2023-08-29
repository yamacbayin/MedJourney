package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class HotelReservationWithRoomResponseDTO extends BaseResponseDTO {

    private Date checkIn;

    private Date checkOut;

    private PatientPlainResponseDTO guest;

    private HotelRoomPlainResponseDTO room;

}
