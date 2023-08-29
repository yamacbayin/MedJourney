package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HotelRoomPlainResponseDTO extends BaseResponseDTO {

    private Integer number;

    private BigDecimal price;

}
