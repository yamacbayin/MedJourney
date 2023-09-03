package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.HotelEntity;
import com.yamacbayin.medjourney.database.entity.HotelReservationEntity;
import com.yamacbayin.medjourney.database.entity.HotelRoomEntity;
import com.yamacbayin.medjourney.database.repository.HotelRoomRepository;
import com.yamacbayin.medjourney.database.specification.HotelRoomSpecification;
import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.mapper.HotelRoomMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelRoomRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelRoomResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelRoomService extends BaseService<
        HotelRoomEntity, HotelRoomResponseDTO, HotelRoomRequestDTO,
        HotelRoomRepository, HotelRoomMapper, HotelRoomSpecification> {

    private final HotelRoomRepository hotelRoomRepository;
    private final HotelRoomSpecification hotelRoomSpecification;

    private final HotelService hotelService;

    @Override
    protected String getEntityName() {
        return "Room";
    }

    @Override
    protected HotelRoomMapper getMapper() {
        return HotelRoomMapper.INSTANCE;
    }

    @Override
    protected HotelRoomRepository getRepository() {
        return this.hotelRoomRepository;
    }

    @Override
    protected HotelRoomSpecification getSpecification() {
        return hotelRoomSpecification;
    }

    @Override
    @Transactional
    public HotelRoomResponseDTO save(HotelRoomRequestDTO hotelRoomRequestDTO) {
        HotelEntity hotel = hotelService.getEntityByUuid(hotelRoomRequestDTO.getHotelUuid());

        if (hotel == null) {
            throw new InvalidUuidException("Hotel UUID is not valid.");
        }

        HotelRoomEntity room = getMapper().requestDtoToEntity(hotelRoomRequestDTO);
        room.setHotel(hotel);
        return getMapper().entityToResponseDto(getRepository().save(room));
    }

    @Override
    @Transactional
    public HotelRoomResponseDTO update(UUID uuid, HotelRoomRequestDTO hotelRoomRequestDTO) {

        HotelRoomEntity room = getEntityByUuid(uuid);

        if (room == null) {
            throw new InvalidUuidException("Room UUID is not valid.");
        }

        if (hotelRoomRequestDTO.getHotelUuid() != null
                && room.getHotel().getUuid() != hotelRoomRequestDTO.getHotelUuid()) {

            HotelEntity hotel = hotelService.getEntityByUuid(hotelRoomRequestDTO.getHotelUuid());

            if (hotel == null) {
                throw new InvalidUuidException("Hotel UUID is not valid.");
            }

            hotelService.removeRoomFromHotel(room.getHotel(), room);
            room.setHotel(hotel);
        }

        room = getMapper().updateEntityFromRequestDTO(hotelRoomRequestDTO, room);
        return getMapper().entityToResponseDto(getRepository().save(room));

    }

    public boolean isRoomAvailable(HotelRoomEntity room, Date checkIn, Date checkOut) {
        List<HotelReservationEntity> reservations = room.getReservationList();

        for (HotelReservationEntity reservation : reservations) {
            Date existingCheckIn = reservation.getCheckIn();
            Date existingCheckOut = reservation.getCheckOut();

            // Check for date range overlap
            if (checkIn.before(existingCheckOut) && checkOut.after(existingCheckIn)) {
                // There is an overlap, room is not available
                return false;
            }
        }

        // No overlap found, room is available
        return true;
    }
}
