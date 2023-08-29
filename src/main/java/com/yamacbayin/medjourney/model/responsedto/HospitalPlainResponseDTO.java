package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.database.entity.AddressEntity;
import lombok.Data;

@Data
public class HospitalPlainResponseDTO {

    private String name;

    private AddressEntity address;

}
