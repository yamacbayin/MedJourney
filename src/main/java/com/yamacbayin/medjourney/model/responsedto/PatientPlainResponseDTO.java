package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PatientPlainResponseDTO extends BaseResponseDTO {

    private String name;

    private BigDecimal balance;

    private AddressResponseDTO address;

}
