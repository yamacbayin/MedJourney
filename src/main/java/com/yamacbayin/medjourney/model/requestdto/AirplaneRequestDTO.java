package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

@Data
public class AirplaneRequestDTO {

    private String manufacturer;

    private String model;

    private Integer passengerCapacity;

    private String registrationId;
}
