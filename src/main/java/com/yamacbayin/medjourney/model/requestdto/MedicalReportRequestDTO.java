package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.util.UUID;

@Data
public class MedicalReportRequestDTO {

    private UUID appointmentUuid;

    private String note;

}
