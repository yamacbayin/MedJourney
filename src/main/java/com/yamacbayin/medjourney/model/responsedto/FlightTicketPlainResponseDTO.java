package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class FlightTicketPlainResponseDTO extends BaseResponseDTO {

    private PatientPlainResponseDTO passenger;

}
