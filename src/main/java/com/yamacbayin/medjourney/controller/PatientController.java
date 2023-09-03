package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.PatientEntity;
import com.yamacbayin.medjourney.database.repository.PatientRepository;
import com.yamacbayin.medjourney.database.specification.PatientSpecification;
import com.yamacbayin.medjourney.mapper.PatientMapper;
import com.yamacbayin.medjourney.model.requestdto.PatientRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.PatientResponseDTO;
import com.yamacbayin.medjourney.service.PatientService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
public class PatientController extends BaseController<
        PatientEntity, PatientResponseDTO, PatientRequestDTO,
        PatientRepository, PatientMapper, PatientSpecification, PatientService> {

    private final PatientService patientService;

    @Override
    protected PatientService getService() {
        return this.patientService;
    }
}
