package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.FlightSeatEntity;
import com.yamacbayin.medjourney.database.repository.FlightSeatRepository;
import com.yamacbayin.medjourney.database.specification.FlightSeatSpecification;
import com.yamacbayin.medjourney.mapper.FlightSeatMapper;
import com.yamacbayin.medjourney.model.requestdto.FlightSeatRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightSeatResponseDTO;
import com.yamacbayin.medjourney.service.FlightSeatService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flight-seats")
@RequiredArgsConstructor
public class FlightSeatController extends BaseController<
        FlightSeatEntity, FlightSeatResponseDTO, FlightSeatRequestDTO,
        FlightSeatRepository, FlightSeatMapper, FlightSeatSpecification, FlightSeatService> {

    private final FlightSeatService flightSeatService;

    @Override
    protected FlightSeatService getService() {
        return this.flightSeatService;
    }
}
