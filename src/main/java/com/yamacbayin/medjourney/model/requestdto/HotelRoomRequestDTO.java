package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class HotelRoomRequestDTO {

    private Integer number;

    private BigDecimal price;

    private UUID hotelUuid;

}
