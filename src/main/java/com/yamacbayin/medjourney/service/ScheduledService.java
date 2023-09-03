package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledService {

    private final AppointmentService appointmentService;

    @Scheduled(fixedRate = 30000)
    public void checkTimeouts() {

        List<AppointmentEntity> appointments = appointmentService
                .getRepository().findAllByStatus(AppointmentStatusEnum.CREATION);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -10);
        Date tenMinutesBefore = calendar.getTime();


        for (AppointmentEntity appointment : appointments) {

            long tenMinutesBeforeInSeconds = tenMinutesBefore.getTime() / 1000;
            long creationDateInSeconds = appointment.getCreationDate().getTime() / 1000;

            if (tenMinutesBeforeInSeconds > creationDateInSeconds) {
                // The appointment is not confirmed within 10 minutes, mark it as invalid
                appointmentService.cancelAppointment(appointment.getUuid());
            }
        }
    }

}
