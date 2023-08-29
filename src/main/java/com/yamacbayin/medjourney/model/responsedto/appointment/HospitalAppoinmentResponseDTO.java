package com.yamacbayin.medjourney.model.responsedto.appointment;

import com.yamacbayin.medjourney.database.entity.AddressEntity;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class HospitalAppoinmentResponseDTO extends BaseResponseDTO {

    private String name;

    private AddressEntity address;

}
