package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.DoctorEntity;
import com.yamacbayin.medjourney.database.entity.HospitalEntity;
import com.yamacbayin.medjourney.database.repository.HospitalRepository;
import com.yamacbayin.medjourney.database.specification.HospitalSpecification;
import com.yamacbayin.medjourney.mapper.HospitalMapper;
import com.yamacbayin.medjourney.model.requestdto.HospitalRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HospitalResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService extends BaseService<
        HospitalEntity, HospitalResponseDTO, HospitalRequestDTO,
        HospitalRepository, HospitalMapper, HospitalSpecification> {

    private final HospitalRepository hospitalRepository;
    private final HospitalSpecification hospitalSpecification;

    @Override
    protected String getEntityName() {
        return "Hospital";
    }

    @Override
    protected HospitalMapper getMapper() {
        return HospitalMapper.INSTANCE;
    }

    @Override
    protected HospitalRepository getRepository() {
        return this.hospitalRepository;
    }

    @Override
    protected HospitalSpecification getSpecification() {
        return hospitalSpecification;
    }

    public void removeDoctorFromHospital(HospitalEntity hospital, DoctorEntity doctor) {
        hospital.getDoctorList().remove(doctor);
        doctor.setHospital(null);

        hospitalRepository.save(hospital);
    }

}
