package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class MedicalReportResponseDTO extends BaseResponseDTO {

    private DoctorPlainResponseDTO doctor;

    private AppointmentPlainResponseDTO appointmentEntity;

    private String note;

}
