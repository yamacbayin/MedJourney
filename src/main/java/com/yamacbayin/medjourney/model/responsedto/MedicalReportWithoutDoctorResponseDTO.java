package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class MedicalReportWithoutDoctorResponseDTO extends BaseResponseDTO {

    private AppointmentPlainResponseDTO appointmentEntity;

    private String note;

}
