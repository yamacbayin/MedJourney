package com.yamacbayin.medjourney.controller;

import com.yamacbayin.medjourney.database.entity.MedicalReportEntity;
import com.yamacbayin.medjourney.database.repository.MedicalReportRepository;
import com.yamacbayin.medjourney.database.specification.MedicalReportSpecification;
import com.yamacbayin.medjourney.mapper.MedicalReportMapper;
import com.yamacbayin.medjourney.model.requestdto.MedicalReportRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.MedicalReportResponseDTO;
import com.yamacbayin.medjourney.service.MedicalReportService;
import com.yamacbayin.medjourney.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medical-reports")
@RequiredArgsConstructor
public class MedicalReportController extends BaseController<
        MedicalReportEntity, MedicalReportResponseDTO, MedicalReportRequestDTO,
        MedicalReportRepository, MedicalReportMapper, MedicalReportSpecification, MedicalReportService> {

    private final MedicalReportService medicalReportService;

    @Override
    protected MedicalReportService getService() {
        return this.medicalReportService;
    }
}
