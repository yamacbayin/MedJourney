package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DoctorResponseDTO extends BaseResponseDTO {

    private String name;

    private String branch;

    private BigDecimal appointmentPrice;

    private HospitalPlainResponseDTO hospital;

    private List<AppointmentPlainResponseDTO> appointmentList;

    private List<MedicalReportWithoutDoctorResponseDTO> medicalReportList;
}
