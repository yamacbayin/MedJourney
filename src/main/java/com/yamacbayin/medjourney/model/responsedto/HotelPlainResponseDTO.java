package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.database.entity.AddressEntity;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

@Data
public class HotelPlainResponseDTO extends BaseResponseDTO {

    private String name;

    private Integer stars;

    private AddressEntity address;
}
