package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.DoctorEntity;
import com.yamacbayin.medjourney.database.repository.DoctorRepository;
import com.yamacbayin.medjourney.database.specification.DoctorSpecification;
import com.yamacbayin.medjourney.mapper.DoctorMapper;
import com.yamacbayin.medjourney.model.requestdto.DoctorRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.DoctorResponseDTO;
import com.yamacbayin.medjourney.service.DoctorService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
@RequiredArgsConstructor
public class DoctorController extends BaseController<
        DoctorEntity, DoctorResponseDTO, DoctorRequestDTO,
        DoctorRepository, DoctorMapper, DoctorSpecification, DoctorService> {

    private final DoctorService doctorService;

    @Override
    protected DoctorService getService() {
        return this.doctorService;
    }
}
