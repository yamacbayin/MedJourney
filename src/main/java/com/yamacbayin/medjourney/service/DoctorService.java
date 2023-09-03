package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.database.entity.DoctorEntity;
import com.yamacbayin.medjourney.database.entity.HospitalEntity;
import com.yamacbayin.medjourney.database.repository.DoctorRepository;
import com.yamacbayin.medjourney.database.specification.DoctorSpecification;
import com.yamacbayin.medjourney.mapper.DoctorMapper;
import com.yamacbayin.medjourney.model.requestdto.DoctorRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.DoctorResponseDTO;
import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService extends BaseService<
        DoctorEntity, DoctorResponseDTO, DoctorRequestDTO, DoctorRepository, DoctorMapper, DoctorSpecification> {

    private final DoctorRepository doctorRepository;
    private final DoctorSpecification doctorSpecification;

    private final HospitalService hospitalService;

    @Override
    protected String getEntityName() {
        return "Doctor";
    }

    @Override
    protected DoctorMapper getMapper() {
        return DoctorMapper.INSTANCE;
    }

    @Override
    protected DoctorRepository getRepository() {
        return this.doctorRepository;
    }

    @Override
    protected DoctorSpecification getSpecification() {
        return doctorSpecification;
    }

    @Override
    @Transactional
    public DoctorResponseDTO save(DoctorRequestDTO doctorRequestDTO) {
        HospitalEntity hospital = hospitalService.getEntityByUuid(doctorRequestDTO.getHospitalUuid());

        DoctorEntity doctor = getMapper().requestDtoToEntity(doctorRequestDTO);
        doctor.setHospital(hospital);
        return getMapper().entityToResponseDto(getRepository().save(doctor));
    }

    @Override
    @Transactional
    public DoctorResponseDTO update(UUID uuid, DoctorRequestDTO doctorRequestDTO) {

        DoctorEntity doctor = this.getEntityByUuid(uuid);

        if (doctorRequestDTO.getHospitalUuid() != null
                && doctor.getHospital().getUuid() != doctorRequestDTO.getHospitalUuid()) {

            HospitalEntity hospital = hospitalService.getEntityByUuid(doctorRequestDTO.getHospitalUuid());

            hospitalService.removeDoctorFromHospital(doctor.getHospital(), doctor);
            doctor.setHospital(hospital);
        }

        doctor = getMapper().updateEntityFromRequestDTO(doctorRequestDTO, doctor);

        return getMapper().entityToResponseDto(getRepository().save(doctor));
    }

    public boolean isDoctorAvailable(DoctorEntity doctor, Date newStartDateTime) {
        Date newEndDateTime = calculateAppointmentEndDate(newStartDateTime);

        List<AppointmentEntity> appointments = doctor.getAppointmentList();

        for (AppointmentEntity appointment : appointments) {

            Date appointmentStart = appointment.getDate();
            // Assuming each appointment lasts for 30 minutes
            Date appointmentEnd = calculateAppointmentEndDate(appointmentStart);

            // Check for overlap in date and time
            if (appointment.getStatus() != AppointmentStatusEnum.CANCELED &&
                    newStartDateTime.before(appointmentEnd) && newEndDateTime.after(appointmentStart)) {
                // There is an overlap, doctor is not available
                return false;
            }
        }

        // No overlap found, doctor is available
        return true;
    }

    private Date calculateAppointmentEndDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        // Add 30 minutes to the Calendar
        calendar.add(Calendar.MINUTE, 30);

        // Get the updated Date
        return calendar.getTime();
    }

}
