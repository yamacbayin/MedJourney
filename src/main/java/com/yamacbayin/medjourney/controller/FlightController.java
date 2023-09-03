package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.FlightEntity;
import com.yamacbayin.medjourney.database.repository.FlightRepository;
import com.yamacbayin.medjourney.database.specification.FlightSpecification;
import com.yamacbayin.medjourney.mapper.FlightMapper;
import com.yamacbayin.medjourney.model.requestdto.FlightRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightResponseDTO;
import com.yamacbayin.medjourney.service.FlightService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flights")
@RequiredArgsConstructor
public class FlightController extends BaseController<
        FlightEntity, FlightResponseDTO, FlightRequestDTO,
        FlightRepository, FlightMapper, FlightSpecification, FlightService> {

    private final FlightService flightService;

    @Override
    protected FlightService getService() {
        return this.flightService;
    }

}
