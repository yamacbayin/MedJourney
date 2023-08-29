package com.yamacbayin.medjourney.model.requestdto;

import com.yamacbayin.medjourney.model.status.AppointmentFlightTypeEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class AppointmentFlightRequestDTO {

    private UUID seatUuid;

    private AppointmentFlightTypeEnum directionType;

}
