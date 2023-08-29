package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class FlightTicketResponseDTO extends BaseResponseDTO {

    private FlightPlainResponseDTO flight;

    private FlightSeatPlainResponseDTO seat;

    private PatientPlainResponseDTO passenger;

}
