package com.yamacbayin.medjourney.model.responsedto.appointment;

import com.yamacbayin.medjourney.model.responsedto.HotelPlainResponseDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelRoomPlainResponseDTO;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class HotelReservationAppointmentResponseDTO extends BaseResponseDTO {

    private Date checkIn;

    private Date checkOut;

    private HotelPlainResponseDTO hotel;

    private HotelRoomPlainResponseDTO room;

}
