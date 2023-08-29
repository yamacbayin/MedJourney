package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.DoctorEntity;
import com.yamacbayin.medjourney.database.entity.HospitalEntity;
import com.yamacbayin.medjourney.database.repository.DoctorRepository;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.mapper.DoctorMapper;
import com.yamacbayin.medjourney.model.requestdto.DoctorRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.DoctorResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService extends BaseService<
        DoctorEntity, DoctorResponseDTO, DoctorRequestDTO, DoctorRepository, DoctorMapper> {

    private final DoctorRepository doctorRepository;

    private final HospitalService hospitalService;

    @Override
    protected DoctorMapper getMapper() {
        return DoctorMapper.INSTANCE;
    }

    @Override
    protected DoctorRepository getRepository() {
        return this.doctorRepository;
    }

    @Override
    @Transactional
    public DoctorResponseDTO save(DoctorRequestDTO doctorRequestDTO) {
        HospitalEntity hospital = hospitalService.getEntityByUuid(doctorRequestDTO.getHospitalUuid());

        if (hospital == null) {
            throw new InvalidUuidException("Hospital UUID is not valid.");
        }

        DoctorEntity doctor = getMapper().requestDtoToEntity(doctorRequestDTO);
        doctor.setHospital(hospital);
        return getMapper().entityToResponseDto(getRepository().save(doctor));
    }

    @Override
    @Transactional
    public DoctorResponseDTO update(UUID uuid, DoctorRequestDTO doctorRequestDTO) {

        DoctorEntity doctor = this.getEntityByUuid(uuid);

        if (doctor == null) {
            throw new InvalidUuidException("Doctor UUID is not valid.");
        }

        if (doctorRequestDTO.getHospitalUuid() != null
                && doctor.getHospital().getUuid() != doctorRequestDTO.getHospitalUuid()) {

            HospitalEntity hospital = hospitalService.getEntityByUuid(doctorRequestDTO.getHospitalUuid());

            if (hospital == null) {
                throw new InvalidUuidException("Hospital UUID is not valid.");
            }

            hospitalService.removeDoctorFromHospital(doctor.getHospital(), doctor);
            doctor.setHospital(hospital);
        }

        doctor = getMapper().updateEntityFromRequestDTO(doctorRequestDTO, doctor);

        return getMapper().entityToResponseDto(getRepository().save(doctor));
    }
}
