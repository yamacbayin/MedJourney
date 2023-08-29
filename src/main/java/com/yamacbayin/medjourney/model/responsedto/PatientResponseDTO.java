package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PatientResponseDTO extends BaseResponseDTO {

    private String name;

    private BigDecimal balance;

    private AddressResponseDTO address;

    private List<AppointmentPatientResponseDTO> appointmentList;

    //private List<MedicalReportPatientResponseDto> medicalReportList;

}
