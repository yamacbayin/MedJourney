package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FlightResponseDTO extends BaseResponseDTO {

    private AirplaneResponseDTO airplane;

    private List<FlightSeatPlainResponseDTO> seatList;

    private AddressResponseDTO departureLocation;

    private AddressResponseDTO arrivalLocation;

    private Date flightDate;

}
