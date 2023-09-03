package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.*;
import com.yamacbayin.medjourney.database.repository.AppointmentRepository;
import com.yamacbayin.medjourney.database.specification.AppointmentSpecification;
import com.yamacbayin.medjourney.exception.AlreadySelectedException;
import com.yamacbayin.medjourney.exception.AppointmentException;
import com.yamacbayin.medjourney.exception.InvalidFlightException;
import com.yamacbayin.medjourney.exception.ReservationNotAvailableException;
import com.yamacbayin.medjourney.mapper.AppointmentMapper;
import com.yamacbayin.medjourney.model.requestdto.AppointmentCreationRequestDTO;
import com.yamacbayin.medjourney.model.requestdto.AppointmentFlightRequestDTO;
import com.yamacbayin.medjourney.model.requestdto.AppointmentHotelRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AppointmentResponseDTO;
import com.yamacbayin.medjourney.model.status.AppointmentFlightTypeEnum;
import com.yamacbayin.medjourney.model.status.AppointmentStatusEnum;
import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService extends BaseService<
        AppointmentEntity, AppointmentResponseDTO,
        AppointmentCreationRequestDTO, AppointmentRepository, AppointmentMapper, AppointmentSpecification> {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentSpecification appointmentSpecification;

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final FlightSeatService seatService;
    private final FlightTicketService flightTicketService;
    private final HotelReservationService hotelReservationService;

    @Override
    protected String getEntityName() {
        return "Appointment";
    }

    @Override
    protected AppointmentMapper getMapper() {
        return AppointmentMapper.INSTANCE;
    }

    @Override
    protected AppointmentRepository getRepository() {
        return this.appointmentRepository;
    }

    @Override
    protected AppointmentSpecification getSpecification() {
        return appointmentSpecification;
    }

    @Override
    @Transactional
    public AppointmentResponseDTO save(AppointmentCreationRequestDTO appointmentCreationRequestDTO) {
        DoctorEntity doctor = doctorService.getEntityByUuid(appointmentCreationRequestDTO.getDoctorUuid());
        PatientEntity patient = patientService.getEntityByUuid(appointmentCreationRequestDTO.getPatientUuid());

        if (appointmentCreationRequestDTO.getDate().before(new Date())) {
            throw new ReservationNotAvailableException("Cannot create an appointment for the past.");
        }

        if (!doctorService.isDoctorAvailable(doctor, appointmentCreationRequestDTO.getDate())) {
            throw new ReservationNotAvailableException("The doctor you selected is not available at this hour ("
                    + appointmentCreationRequestDTO.getDate() + "). " +
                    "Please choose another doctor or time.");
        }

        AppointmentEntity appointment = getMapper().requestDtoToEntity(appointmentCreationRequestDTO);
        appointment.setDoctor(doctor);
        appointment.setHospital(doctor.getHospital());
        appointment.setPatient(patient);
        appointment.setStatus(AppointmentStatusEnum.CREATION);

        return getMapper().entityToResponseDto(getRepository().save(appointment));
    }


    @Transactional
    public AppointmentResponseDTO addFlightTicket(UUID uuid, AppointmentFlightRequestDTO flightRequestDTO) {
        AppointmentEntity appointment = getEntityByUuid(uuid);
        // date must be between now and appointment date
        FlightSeatEntity seat = seatService.getEntityByUuid(flightRequestDTO.getSeatUuid());

        if (seat.getFlight().getFlightDate().before(new Date())) {
            throw new ReservationNotAvailableException("Cannot buy tickets for the past flights.");
        }

        if (flightRequestDTO.getDirectionType() == AppointmentFlightTypeEnum.DEPARTURE) {

            if (appointment.getDepartureFlight() != null) {
                throw new AlreadySelectedException("A departure flight is already selected for this appointment.");
            }

            if (seat.getFlight().getFlightDate().after(appointment.getDate())) {
                throw new ReservationNotAvailableException("Selected flight's date is not valid for this appointment. " +
                        "PLease choose another flight.");
            }

            if (!appointment.getPatient().getAddress().getCountry()
                    .equalsIgnoreCase(seat.getFlight().getDepartureLocation().getCountry())
                    || !appointment.getPatient().getAddress().getCity()
                    .equalsIgnoreCase(seat.getFlight().getDepartureLocation().getCity())) {
                throw new InvalidFlightException("The flight doesn't depart from the patient's location.");
            }

            if (!appointment.getHospital().getAddress().getCountry()
                    .equalsIgnoreCase(seat.getFlight().getArrivalLocation().getCountry())
                    || !appointment.getHospital().getAddress().getCity()
                    .equalsIgnoreCase(seat.getFlight().getArrivalLocation().getCity())) {
                throw new InvalidFlightException("The flight doesn't land at the hospital's location.");
            }

            FlightTicketEntity ticket = flightTicketService.buySeatByUuid(
                    flightRequestDTO.getSeatUuid(), appointment.getPatient());
            appointment.setDepartureFlight(ticket);


        } else if (flightRequestDTO.getDirectionType() == AppointmentFlightTypeEnum.RETURN) {

            if (appointment.getReturnFlight() != null) {
                throw new AlreadySelectedException("A return flight is already selected for this appointment.");
            }

            if (seat.getFlight().getFlightDate().before(appointment.getDate())) {
                throw new ReservationNotAvailableException("Selected flight's date is not valid for this appointment. " +
                        "PLease choose another flight.");
            }

            if (!appointment.getHospital().getAddress().getCountry()
                    .equalsIgnoreCase(seat.getFlight().getDepartureLocation().getCountry())
                    || !appointment.getHospital().getAddress().getCity()
                    .equalsIgnoreCase(seat.getFlight().getDepartureLocation().getCity())) {
                throw new InvalidFlightException("The flight doesn't depart from the hospital's location.");
            }

            if (!appointment.getPatient().getAddress().getCountry()
                    .equalsIgnoreCase(seat.getFlight().getArrivalLocation().getCountry())
                    || !appointment.getPatient().getAddress().getCity()
                    .equalsIgnoreCase(seat.getFlight().getArrivalLocation().getCity())) {
                throw new InvalidFlightException("The flight doesn't land at the patient's location.");
            }

            FlightTicketEntity ticket = flightTicketService.buySeatByUuid(
                    flightRequestDTO.getSeatUuid(), appointment.getPatient());
            appointment.setReturnFlight(ticket);
        }

        return getMapper().entityToResponseDto(getRepository().save(appointment));
    }


    @Transactional
    public AppointmentResponseDTO addHotelReservation(UUID uuid, AppointmentHotelRequestDTO hotelRequestDTO) {
        AppointmentEntity appointment = getEntityByUuid(uuid);

        if (appointment.getHotelReservation() != null) {
            throw new AlreadySelectedException("A hotel reservation is already selected for this appointment.");
        }

        if (appointment.getDepartureFlight() == null) {
            throw new AppointmentException("Must choose a departure flight before creating a hotel reservation");
        }

        if (appointment.getReturnFlight() == null) {
            throw new AppointmentException("Must choose a return flight before creating a hotel reservation");
        }

        Date checkIn = appointment.getDepartureFlight().getFlight().getFlightDate();
        Date checkOut = appointment.getReturnFlight().getFlight().getFlightDate();

        HotelReservationEntity reservation = hotelReservationService.createHotelReservation(
                hotelRequestDTO.getRoomUuid(), checkIn, checkOut, appointment.getPatient());

        if (!reservation.getHotel().getAddress().getCountry()
                .equalsIgnoreCase(appointment.getHospital().getAddress().getCountry())
                || !reservation.getHotel().getAddress().getCity()
                .equalsIgnoreCase(appointment.getHospital().getAddress().getCity())) {
            throw new AppointmentException("The hotel must be in the same location with the hospital.");
        }

        appointment.setHotelReservation(reservation);
        seatService.updateSeatStatus(appointment.getDepartureFlight().getSeat(), SeatStatusEnum.SOLD);
        seatService.updateSeatStatus(appointment.getReturnFlight().getSeat(), SeatStatusEnum.SOLD);
        appointment.setStatus(AppointmentStatusEnum.SCHEDULED);

        return getMapper().entityToResponseDto(getRepository().save(appointment));
    }

    @Override
    @Transactional
    public AppointmentResponseDTO update(UUID uuid, AppointmentCreationRequestDTO appointmentCreationRequestDTO) {
        throw new AppointmentException("You cannot make changes on your appointment. " +
                "Cancel your appointment and create a new one");
    }

    @Transactional
    public AppointmentResponseDTO cancelAppointment(UUID uuid) {
        AppointmentEntity appointment = getEntityByUuid(uuid);

        if(appointment.getDepartureFlight() != null) {
            flightTicketService.refundTicket(appointment.getDepartureFlight().getUuid());
        }
        if(appointment.getReturnFlight() != null) {
            flightTicketService.refundTicket(appointment.getReturnFlight().getUuid());
        }
        if(appointment.getHotelReservation() != null) {
            hotelReservationService.refundReservation(appointment.getHotelReservation().getUuid());
        }

        appointment.setStatus(AppointmentStatusEnum.CANCELED);
        return getMapper().entityToResponseDto(getRepository().save(appointment));
    }

    @Transactional
    public AppointmentEntity addMedicalReportToAppointment(AppointmentEntity appointment, MedicalReportEntity report) {
        appointment.setReport(report);
        return getRepository().save(appointment);
    }


}
