package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PatientRequestDTO {

    private String name;

    private BigDecimal balance;

    private AddressRequestDTO address;

}
