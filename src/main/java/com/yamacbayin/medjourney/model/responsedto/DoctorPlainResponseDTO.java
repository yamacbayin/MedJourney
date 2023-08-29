package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class DoctorPlainResponseDTO extends BaseResponseDTO {

    private String name;

    private String branch;

    private BigDecimal appointmentPrice;

}