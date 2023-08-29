package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class AirplaneResponseDTO extends BaseResponseDTO {

    private String manufacturer;

    private String model;

    private Integer passengerCapacity;

    private String registrationId;

}
