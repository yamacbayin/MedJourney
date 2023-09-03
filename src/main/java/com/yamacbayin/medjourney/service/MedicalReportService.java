package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.database.entity.MedicalReportEntity;
import com.yamacbayin.medjourney.database.repository.MedicalReportRepository;
import com.yamacbayin.medjourney.database.specification.MedicalReportSpecification;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.mapper.MedicalReportMapper;
import com.yamacbayin.medjourney.model.requestdto.MedicalReportRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.MedicalReportResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalReportService extends BaseService<
        MedicalReportEntity, MedicalReportResponseDTO, MedicalReportRequestDTO,
        MedicalReportRepository, MedicalReportMapper, MedicalReportSpecification> {

    private final MedicalReportRepository medicalReportRepository;
    private final MedicalReportSpecification medicalReportSpecification;

    private final AppointmentService appointmentService;

    @Override
    protected String getEntityName() {
        return "Medical Report";
    }

    @Override
    protected MedicalReportMapper getMapper() {
        return MedicalReportMapper.INSTANCE;
    }

    @Override
    protected MedicalReportRepository getRepository() {
        return this.medicalReportRepository;
    }

    @Override
    protected MedicalReportSpecification getSpecification() {
        return medicalReportSpecification;
    }

    @Override
    @Transactional
    public MedicalReportResponseDTO save(MedicalReportRequestDTO medicalReportRequestDTO) {
        AppointmentEntity appointment = appointmentService
                .getEntityByUuid(medicalReportRequestDTO.getAppointmentUuid());

        MedicalReportEntity medicalReport = new MedicalReportEntity();
        medicalReport.setAppointment(appointment);
        medicalReport.setPatient(appointment.getPatient());
        medicalReport.setDoctor(appointment.getDoctor());
        medicalReport.setNote(medicalReportRequestDTO.getNote());
        medicalReport = getRepository().save(medicalReport);

        appointmentService.addMedicalReportToAppointment(appointment, medicalReport);

        return getMapper().entityToResponseDto(medicalReport);
    }

    @Override
    @Transactional
    public MedicalReportResponseDTO update(UUID uuid, MedicalReportRequestDTO medicalReportRequestDTO) {
        MedicalReportEntity medicalReport = getEntityByUuid(uuid);

        if(medicalReport.getAppointment().getUuid() != medicalReportRequestDTO.getAppointmentUuid()) {
            throw new InvalidUuidException("Changing the appointment of a report is not permitted.");
        }

        return getMapper().entityToResponseDto(
                getRepository().save(
                        getMapper().updateEntityFromRequestDTO(medicalReportRequestDTO, medicalReport)));

    }
}
