package com.yamacbayin.medjourney.model.responsedto.appointment;

import com.yamacbayin.medjourney.model.responsedto.FlightPlainResponseDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightSeatPlainResponseDTO;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class FlightTicketAppointmentResponseDTO extends BaseResponseDTO {

    private FlightPlainResponseDTO flight;

    private FlightSeatPlainResponseDTO seat;

}
