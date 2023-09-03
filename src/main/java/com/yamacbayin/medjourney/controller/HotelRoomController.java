package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.HotelRoomEntity;
import com.yamacbayin.medjourney.database.repository.HotelRoomRepository;
import com.yamacbayin.medjourney.database.specification.HotelRoomSpecification;
import com.yamacbayin.medjourney.mapper.HotelRoomMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelRoomRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelRoomResponseDTO;
import com.yamacbayin.medjourney.service.HotelRoomService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel-rooms")
@RequiredArgsConstructor
public class HotelRoomController extends BaseController<
        HotelRoomEntity, HotelRoomResponseDTO, HotelRoomRequestDTO,
        HotelRoomRepository, HotelRoomMapper, HotelRoomSpecification, HotelRoomService> {

    private final HotelRoomService hotelRoomService;

    @Override
    protected HotelRoomService getService() {
        return this.hotelRoomService;
    }
}
