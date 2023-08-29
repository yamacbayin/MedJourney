package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlightSeatResponseDTO extends BaseResponseDTO {

    private Integer seatNumber;

    private SeatStatusEnum status;

    private BigDecimal price;

    private FlightPlainResponseDTO flight;

    private FlightTicketPlainResponseDTO ticket;

}
