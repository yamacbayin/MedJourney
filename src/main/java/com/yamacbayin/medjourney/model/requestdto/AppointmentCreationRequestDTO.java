package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AppointmentCreationRequestDTO {

    private UUID doctorUuid;

    private Date date;

    private UUID patientUuid;

}
