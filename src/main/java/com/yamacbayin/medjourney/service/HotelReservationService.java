package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.HotelReservationEntity;
import com.yamacbayin.medjourney.database.entity.HotelRoomEntity;
import com.yamacbayin.medjourney.database.entity.PatientEntity;
import com.yamacbayin.medjourney.database.repository.HotelReservationRepository;
import com.yamacbayin.medjourney.exception.InsufficientFundsException;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.exception.ReservationNotAvailableException;
import com.yamacbayin.medjourney.mapper.HotelReservationMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelReservationRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelReservationResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelReservationService extends BaseService<
        HotelReservationEntity, HotelReservationResponseDTO,
        HotelReservationRequestDTO, HotelReservationRepository, HotelReservationMapper> {

    private final HotelReservationRepository hotelReservationRepository;
    //private final HotelService hotelService;
    private final HotelRoomService roomService;
    private final PatientService patientService;

    @Override
    protected HotelReservationMapper getMapper() {
        return HotelReservationMapper.INSTANCE;
    }

    @Override
    protected HotelReservationRepository getRepository() {
        return this.hotelReservationRepository;
    }

    @Override
    @Transactional
    public HotelReservationResponseDTO save(HotelReservationRequestDTO hotelReservationRequestDTO) {
        HotelRoomEntity room = roomService.getEntityByUuid(hotelReservationRequestDTO.getRoomUuid());
        PatientEntity patient = patientService.getEntityByUuid(hotelReservationRequestDTO.getGuestUuid());

        if (room == null) {
            throw new InvalidUuidException("Room UUID is not valid.");
        } else if (patient == null) {
            throw new InvalidUuidException("Guest UUID is not valid.");
        }

        if (!roomService.isRoomAvailable(room,
                hotelReservationRequestDTO.getCheckIn(), hotelReservationRequestDTO.getCheckOut())) {
            throw new ReservationNotAvailableException("The room is already reserved for the selected date. " +
                    "Please choose another date or room.");
        } else if (patient.getBalance().compareTo(room.getPrice()) < 0) {
            throw new InsufficientFundsException("Insufficient balance to reserve this room. " +
                    "Please add funds to your account to proceed.");
        }

        patient = patientService.deductPaymentFromBalance(patient, room.getPrice());

        HotelReservationEntity reservation = getMapper().requestDtoToEntity(hotelReservationRequestDTO);
        reservation.setRoom(room);
        reservation.setHotel(room.getHotel());
        reservation.setGuest(patient);

        return getMapper().entityToResponseDto(getRepository().save(reservation));
    }

    @Override
    @Transactional
    public HotelReservationResponseDTO update(UUID uuid, HotelReservationRequestDTO hotelReservationRequestDTO) {
        HotelReservationEntity reservation = getEntityByUuid(uuid);

        if (reservation == null) {
            throw new InvalidUuidException("Reservation UUID is not valid.");
        }

        return null;
    }
}
