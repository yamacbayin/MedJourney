package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.model.responsedto.appointment.PatientAppointmentResponseDTO;
import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentPlainResponseDTO extends BaseResponseDTO {

    private AppointmentStatusEnum status;

    private Date date;

    private PatientAppointmentResponseDTO patient;

}
