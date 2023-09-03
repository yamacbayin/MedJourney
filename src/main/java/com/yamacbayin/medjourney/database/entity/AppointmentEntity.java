package com.yamacbayin.medjourney.database.entity;

import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "appointments")
public class AppointmentEntity extends BaseEntity {

    private AppointmentStatusEnum status;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalEntity hospital;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @OneToOne
    @JoinColumn(name = "hotel_reservation_id")
    private HotelReservationEntity hotelReservation;

    @OneToOne
    @JoinColumn(name = "departure_flight_id")
    private FlightTicketEntity departureFlight;

    @OneToOne
    @JoinColumn(name = "return_flight_id")
    private FlightTicketEntity returnFlight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_report_id")
    private MedicalReportEntity report;

}
