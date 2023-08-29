package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.database.entity.AddressEntity;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class HotelResponseDTO extends BaseResponseDTO {

    private String name;

    private Integer stars;

    private AddressEntity address;

    private List<HotelRoomPlainResponseDTO> roomList;

    //private List<HotelReservationWithRoomResponseDTO> reservationList;

}
