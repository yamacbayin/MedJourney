package com.yamacbayin.medjourney.model.responsedto.appointment;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoctorAppointmentResponseDTO extends BaseResponseDTO {

    private String name;

    private String branch;

    private BigDecimal appointmentPrice;

}
