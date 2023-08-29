package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class FlightPlainResponseDTO extends BaseResponseDTO {

    private AddressResponseDTO departureLocation;

    private AddressResponseDTO arrivalLocation;

    private Date flightDate;

}
