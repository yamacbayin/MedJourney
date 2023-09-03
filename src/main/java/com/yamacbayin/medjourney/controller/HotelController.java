package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.HotelEntity;
import com.yamacbayin.medjourney.database.repository.HotelRepository;
import com.yamacbayin.medjourney.database.specification.HotelSpecification;
import com.yamacbayin.medjourney.mapper.HotelMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelResponseDTO;
import com.yamacbayin.medjourney.service.HotelService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotels")
@RequiredArgsConstructor
public class HotelController extends BaseController<
        HotelEntity, HotelResponseDTO, HotelRequestDTO,
        HotelRepository, HotelMapper, HotelSpecification, HotelService> {

    private final HotelService hotelService;

    @Override
    protected HotelService getService() {
        return this.hotelService;
    }
}
