package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.database.entity.MedicalReportEntity;
import com.yamacbayin.medjourney.model.responsedto.appointment.*;
import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentResponseDTO extends BaseResponseDTO {

    private AppointmentStatusEnum status;

    private Date date;

    private HospitalAppoinmentResponseDTO hospital;

    private DoctorAppointmentResponseDTO doctor;

    private PatientAppointmentResponseDTO patient;

    private HotelReservationAppointmentResponseDTO hotelReservationEntity;

    private FlightTicketAppointmentResponseDTO departureFlight;

    private FlightTicketAppointmentResponseDTO returnFlight;

    private MedicalReportEntity report;

}
