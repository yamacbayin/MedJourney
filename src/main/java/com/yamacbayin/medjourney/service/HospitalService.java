package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.DoctorEntity;
import com.yamacbayin.medjourney.database.entity.HospitalEntity;
import com.yamacbayin.medjourney.database.repository.HospitalRepository;
import com.yamacbayin.medjourney.mapper.HospitalMapper;
import com.yamacbayin.medjourney.model.requestdto.HospitalRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HospitalResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService extends BaseService<
        HospitalEntity, HospitalResponseDTO, HospitalRequestDTO, HospitalRepository, HospitalMapper> {

    private final HospitalRepository hospitalRepository;

    @Override
    protected HospitalMapper getMapper() {
        return HospitalMapper.INSTANCE;
    }

    @Override
    protected HospitalRepository getRepository() {
        return this.hospitalRepository;
    }

    public void removeDoctorFromHospital(HospitalEntity hospital, DoctorEntity doctor) {
        hospital.getDoctorList().remove(doctor);
        doctor.setHospital(null);

        hospitalRepository.save(hospital);
    }

}
