package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.HotelReservationEntity;
import com.yamacbayin.medjourney.database.repository.HotelReservationRepository;
import com.yamacbayin.medjourney.database.specification.HotelReservationSpecification;
import com.yamacbayin.medjourney.mapper.HotelReservationMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelReservationRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelReservationResponseDTO;
import com.yamacbayin.medjourney.service.HotelReservationService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel-reservations")
@RequiredArgsConstructor
public class HotelReservationController extends BaseController<
        HotelReservationEntity, HotelReservationResponseDTO, HotelReservationRequestDTO,
        HotelReservationRepository, HotelReservationMapper, HotelReservationSpecification, HotelReservationService> {

    private final HotelReservationService hotelReservationService;

    @Override
    protected HotelReservationService getService() {
        return this.hotelReservationService;
    }
}
