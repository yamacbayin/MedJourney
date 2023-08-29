package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.AirplaneEntity;
import com.yamacbayin.medjourney.database.repository.AirplaneRepository;
import com.yamacbayin.medjourney.mapper.AirplaneMapper;
import com.yamacbayin.medjourney.model.requestdto.AirplaneRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AirplaneResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirplaneService extends BaseService<
        AirplaneEntity, AirplaneResponseDTO, AirplaneRequestDTO, AirplaneRepository, AirplaneMapper> {

    private final AirplaneRepository airplaneRepository;

    @Override
    protected AirplaneMapper getMapper() {
        return AirplaneMapper.INSTANCE;
    }

    @Override
    protected AirplaneRepository getRepository() {
        return this.airplaneRepository;
    }
}
