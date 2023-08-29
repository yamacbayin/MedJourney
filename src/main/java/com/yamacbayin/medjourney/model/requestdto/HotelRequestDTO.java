package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

@Data
public class HotelRequestDTO {

    private String name;

    private Integer stars;

    private AddressRequestDTO address;

}
