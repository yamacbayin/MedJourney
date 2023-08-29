package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class AddressResponseDTO extends BaseResponseDTO {

    private String country;

    private String city;

}
