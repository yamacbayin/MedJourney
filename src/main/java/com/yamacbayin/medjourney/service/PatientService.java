package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.PatientEntity;
import com.yamacbayin.medjourney.database.repository.PatientRepository;
import com.yamacbayin.medjourney.database.specification.PatientSpecification;
import com.yamacbayin.medjourney.mapper.PatientMapper;
import com.yamacbayin.medjourney.model.requestdto.PatientRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.PatientResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PatientService extends BaseService<
        PatientEntity, PatientResponseDTO, PatientRequestDTO, PatientRepository, PatientMapper, PatientSpecification> {

    private final PatientRepository patientRepository;
    private final PatientSpecification patientSpecification;

    @Override
    protected String getEntityName() {
        return "Patient";
    }

    @Override
    protected PatientMapper getMapper() {
        return PatientMapper.INSTANCE;
    }

    @Override
    protected PatientRepository getRepository() {
        return this.patientRepository;
    }

    @Override
    protected PatientSpecification getSpecification() {
        return patientSpecification;
    }

    @Transactional
    public PatientEntity deductPaymentFromBalance(PatientEntity patient, BigDecimal amount) {
        patient.setBalance(patient.getBalance().subtract(amount));
        return patientRepository.save(patient);
    }

    @Transactional
    public PatientEntity refundPaymentToBalance(PatientEntity patient, BigDecimal amount) {
        patient.setBalance(patient.getBalance().add(amount)); // Add the refunded amount to the balance
        return patientRepository.save(patient);
    }

}
