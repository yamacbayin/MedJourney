package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.HospitalEntity;
import com.yamacbayin.medjourney.database.repository.HospitalRepository;
import com.yamacbayin.medjourney.database.specification.HospitalSpecification;
import com.yamacbayin.medjourney.mapper.HospitalMapper;
import com.yamacbayin.medjourney.model.requestdto.HospitalRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HospitalResponseDTO;
import com.yamacbayin.medjourney.service.HospitalService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hospitals")
@RequiredArgsConstructor
public class HospitalController extends BaseController<
        HospitalEntity, HospitalResponseDTO, HospitalRequestDTO,
        HospitalRepository, HospitalMapper, HospitalSpecification, HospitalService> {

    private final HospitalService hospitalService;

    @Override
    protected HospitalService getService() {
        return this.hospitalService;
    }
}
