package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DoctorRequestDTO {

    private String name;

    private String branch;

    private BigDecimal appointmentPrice;

    //TODO: hospital UUID
    private UUID hospitalUuid;

}
