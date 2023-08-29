package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.database.entity.AddressEntity;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class HospitalResponseDTO extends BaseResponseDTO {

    private String name;

    private AddressEntity address;

    private List<DoctorPlainResponseDTO> doctorList;

}
