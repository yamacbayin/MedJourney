package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.FlightSeatEntity;
import com.yamacbayin.medjourney.database.entity.FlightTicketEntity;
import com.yamacbayin.medjourney.database.entity.PatientEntity;
import com.yamacbayin.medjourney.database.repository.FlightTicketRepository;
import com.yamacbayin.medjourney.exception.InsufficientFundsException;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.exception.ReservationNotAvailableException;
import com.yamacbayin.medjourney.mapper.FlightTicketMapper;
import com.yamacbayin.medjourney.model.requestdto.FlightTicketRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightTicketResponseDTO;
import com.yamacbayin.medjourney.model.status.SeatStatusEnum;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightTicketService extends BaseService<
        FlightTicketEntity, FlightTicketResponseDTO, FlightTicketRequestDTO,
        FlightTicketRepository, FlightTicketMapper> {

    private final FlightTicketRepository flightTicketRepository;
    private final FlightSeatService seatService;
    private final PatientService patientService;

    @Override
    protected FlightTicketMapper getMapper() {
        return FlightTicketMapper.INSTANCE;
    }

    @Override
    protected FlightTicketRepository getRepository() {
        return this.flightTicketRepository;
    }

    private FlightSeatEntity getSeatByUuid(UUID uuid) {
        FlightSeatEntity seat = seatService.getEntityByUuid(uuid);

        if (seat == null) {
            throw new InvalidUuidException("Seat UUID is not valid.");
        } else if (seat.getStatus() == SeatStatusEnum.SOLD
                || seat.getStatus() == SeatStatusEnum.RESERVED_TEMPORARY) {
            throw new ReservationNotAvailableException("The seat is already reserved. " +
                    "Please choose another seat.");
        }

        return seat;
    }

    private PatientEntity getPatientByUuid(UUID uuid) {
        PatientEntity patient = patientService.getEntityByUuid(uuid);

        if (patient == null) {
            throw new InvalidUuidException("Seat UUID is not valid.");
        }

        return patient;
    }

    @Override
    @Transactional
    public FlightTicketResponseDTO save(FlightTicketRequestDTO flightTicketRequestDTO) {
        FlightSeatEntity seat = getSeatByUuid(flightTicketRequestDTO.getSeatUuid());

        PatientEntity patient = getPatientByUuid(flightTicketRequestDTO.getPassengerUuid());

        if (patient.getBalance().compareTo(seat.getPrice()) < 0) {
            throw new InsufficientFundsException("Insufficient balance to reserve this seat. " +
                    "Please add funds to your account to proceed.");
        }

        patient = patientService.deductPaymentFromBalance(patient, seat.getPrice());

        FlightTicketEntity ticket = new FlightTicketEntity();
        ticket.setFlight(seat.getFlight());
        ticket.setSeat(seatService.updateSeatStatus(seat, SeatStatusEnum.RESERVED_TEMPORARY));
        ticket.setPassenger(patient);

        return getMapper().entityToResponseDto(getRepository().save(ticket));
    }

    @Override
    @Transactional
    public FlightTicketResponseDTO update(UUID uuid, FlightTicketRequestDTO flightTicketRequestDTO) {

        FlightTicketEntity ticket = getEntityByUuid(uuid);

        if (ticket.getSeat().getUuid() != flightTicketRequestDTO.getSeatUuid()) {

            FlightSeatEntity oldSeat = ticket.getSeat();
            SeatStatusEnum currentStatus = oldSeat.getStatus();
            FlightSeatEntity newSeat = getSeatByUuid(flightTicketRequestDTO.getSeatUuid());

            if (oldSeat.getFlight().getUuid() != newSeat.getFlight().getUuid()) {

                if (ticket.getPassenger().getBalance().compareTo(newSeat.getPrice()) < 0) {
                    throw new InsufficientFundsException("Insufficient balance to reserve this seat. " +
                            "Please add funds to your account to proceed.");
                }

                patientService.refundPaymentToBalance(ticket.getPassenger(), oldSeat.getPrice());
                patientService.deductPaymentFromBalance(ticket.getPassenger(), newSeat.getPrice());
                ticket.setFlight(newSeat.getFlight());
            }

            seatService.updateSeatStatus(oldSeat, SeatStatusEnum.AVAILABLE);
            ticket.setSeat(seatService.updateSeatStatus(newSeat, currentStatus));
            return getMapper().entityToResponseDto(getRepository().save(ticket));

        } else if (ticket.getPassenger().getUuid() != flightTicketRequestDTO.getPassengerUuid()) {
            //TODO throw new error, cant give ticket to someone else
        } else {
            //TODO no changes error
        }

        return null;
    }
}
