package com.yamacbayin.medjourney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MedJourneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedJourneyApplication.class, args);
    }


    //TODO: timer
    /*
        @Scheduled(fixedRate = 60000) // Check every minute
    public void checkAppointmentConfirmations() {
        List<Appointment> appointments = appointmentRepository.findUnconfirmedAppointments();
        LocalDateTime now = LocalDateTime.now();
        for (Appointment appointment : appointments) {
            if (now.minusMinutes(10).isAfter(appointment.getBookingTime())) {
                // The appointment is not confirmed within 10 minutes, mark it as invalid
                appointment.setStatus(AppointmentStatus.INVALID);
                appointmentRepository.save(appointment);
            }
        }
    }
     */
}
