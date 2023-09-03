package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.HotelReservationEntity;
import com.yamacbayin.medjourney.database.entity.HotelRoomEntity;
import com.yamacbayin.medjourney.database.entity.PatientEntity;
import com.yamacbayin.medjourney.database.repository.HotelReservationRepository;
import com.yamacbayin.medjourney.database.specification.HotelReservationSpecification;
import com.yamacbayin.medjourney.exception.InsufficientFundsException;
import com.yamacbayin.medjourney.exception.ReservationNotAvailableException;
import com.yamacbayin.medjourney.mapper.HotelReservationMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelReservationRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelReservationResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelReservationService extends BaseService<
        HotelReservationEntity, HotelReservationResponseDTO,
        HotelReservationRequestDTO, HotelReservationRepository, HotelReservationMapper, HotelReservationSpecification> {

    private final HotelReservationRepository hotelReservationRepository;
    private final HotelReservationSpecification hotelReservationSpecification;

    //private final HotelService hotelService;
    private final HotelRoomService roomService;
    private final PatientService patientService;

    @Override
    protected String getEntityName() {
        return "Reservation";
    }

    @Override
    protected HotelReservationMapper getMapper() {
        return HotelReservationMapper.INSTANCE;
    }

    @Override
    protected HotelReservationRepository getRepository() {
        return this.hotelReservationRepository;
    }

    @Override
    protected HotelReservationSpecification getSpecification() {
        return hotelReservationSpecification;
    }

    @Transactional
    public HotelReservationEntity createHotelReservation(UUID roomUuid, Date checkIn,
                                                         Date checkOut, PatientEntity patient) {
        HotelRoomEntity room = roomService.getEntityByUuid(roomUuid);
        if (!roomService.isRoomAvailable(room, checkIn, checkOut)) {
            throw new ReservationNotAvailableException("The room is already reserved for the selected date. " +
                    "Please choose another date or room.");
        } else if (patient.getBalance().compareTo(room.getPrice()) < 0) {
            throw new InsufficientFundsException("Insufficient balance to reserve this room. " +
                    "Please add funds to your account to proceed.");
        }

        patient = patientService.deductPaymentFromBalance(patient, room.getPrice());

        HotelReservationEntity reservation = new HotelReservationEntity();
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        reservation.setRoom(room);
        reservation.setHotel(room.getHotel());
        reservation.setGuest(patient);

        return getRepository().save(reservation);

    }

    @Transactional
    public HotelReservationEntity createHotelReservation(HotelReservationRequestDTO hotelReservationRequestDTO) {
        HotelRoomEntity room = roomService.getEntityByUuid(hotelReservationRequestDTO.getRoomUuid());
        PatientEntity patient = patientService.getEntityByUuid(hotelReservationRequestDTO.getGuestUuid());

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

        return getRepository().save(reservation);

    }

    @Override
    @Transactional
    public HotelReservationResponseDTO save(HotelReservationRequestDTO hotelReservationRequestDTO) {
/*        HotelRoomEntity room = roomService.getEntityByUuid(hotelReservationRequestDTO.getRoomUuid());
        PatientEntity patient = patientService.getEntityByUuid(hotelReservationRequestDTO.getGuestUuid());

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

        return getMapper().entityToResponseDto(getRepository().save(reservation));*/

        return getMapper().entityToResponseDto(createHotelReservation(hotelReservationRequestDTO));
    }

    @Transactional
    public void refundReservation(UUID reservationUuid) {
        HotelReservationEntity reservation = getEntityByUuid(reservationUuid);
        patientService.refundPaymentToBalance(reservation.getGuest(), reservation.getRoom().getPrice());
        deleteByUuid(reservation.getUuid());
    }

    @Override
    @Transactional
    public HotelReservationResponseDTO update(UUID uuid, HotelReservationRequestDTO hotelReservationRequestDTO) {
        HotelReservationEntity reservation = getEntityByUuid(uuid);

        patientService.refundPaymentToBalance(reservation.getGuest(), reservation.getRoom().getPrice());
        deleteByUuid(reservation.getUuid());

        //recreate
        if (hotelReservationRequestDTO.getCheckIn() == null) {
            hotelReservationRequestDTO.setCheckIn(reservation.getCheckIn());
        }

        if (hotelReservationRequestDTO.getCheckOut() == null) {
            hotelReservationRequestDTO.setCheckOut(reservation.getCheckOut());
        }

        if (hotelReservationRequestDTO.getRoomUuid() == null) {
            hotelReservationRequestDTO.setRoomUuid(reservation.getRoom().getUuid());
        }

        if (hotelReservationRequestDTO.getGuestUuid() == null) {
            hotelReservationRequestDTO.setGuestUuid(reservation.getGuest().getUuid());
        }

        return getMapper().entityToResponseDto(createHotelReservation(hotelReservationRequestDTO));
    }
}
