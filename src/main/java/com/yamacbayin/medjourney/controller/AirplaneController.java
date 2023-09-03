package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.AirplaneEntity;
import com.yamacbayin.medjourney.database.repository.AirplaneRepository;
import com.yamacbayin.medjourney.database.specification.AirplaneSpecification;
import com.yamacbayin.medjourney.mapper.AirplaneMapper;
import com.yamacbayin.medjourney.model.requestdto.AirplaneRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AirplaneResponseDTO;
import com.yamacbayin.medjourney.service.AirplaneService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("airplanes")
@RequiredArgsConstructor
public class AirplaneController extends BaseController<
        AirplaneEntity, AirplaneResponseDTO, AirplaneRequestDTO,
        AirplaneRepository, AirplaneMapper, AirplaneSpecification, AirplaneService> {

    private final AirplaneService airplaneService;

    @Override
    protected AirplaneService getService() {
        return this.airplaneService;
    }
}
