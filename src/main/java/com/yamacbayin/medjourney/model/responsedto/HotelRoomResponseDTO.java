package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HotelRoomResponseDTO extends BaseResponseDTO {

    private Integer number;

    private BigDecimal price;

    private HotelPlainResponseDTO hotel;

    private List<HotelReservationWithoutRoomResponseDTO> reservationList;

}
