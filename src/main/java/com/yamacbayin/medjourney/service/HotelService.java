package com.yamacbayin.medjourney.service;

import com.yamacbayin.medjourney.database.entity.HotelEntity;
import com.yamacbayin.medjourney.database.entity.HotelRoomEntity;
import com.yamacbayin.medjourney.database.repository.HotelRepository;
import com.yamacbayin.medjourney.database.specification.HotelSpecification;
import com.yamacbayin.medjourney.mapper.HotelMapper;
import com.yamacbayin.medjourney.model.requestdto.HotelRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelResponseDTO;
import com.yamacbayin.medjourney.util.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService extends BaseService<
        HotelEntity, HotelResponseDTO, HotelRequestDTO, HotelRepository, HotelMapper, HotelSpecification> {

    private final HotelRepository hotelRepository;
    private final HotelSpecification hotelSpecification;

    @Override
    protected String getEntityName() {
        return "Hotel";
    }

    @Override
    protected HotelMapper getMapper() {
        return HotelMapper.INSTANCE;
    }

    @Override
    protected HotelRepository getRepository() {
        return this.hotelRepository;
    }

    @Override
    protected HotelSpecification getSpecification() {
        return hotelSpecification;
    }

    public void removeRoomFromHotel(HotelEntity hotel, HotelRoomEntity room) {
        hotel.getRoomList().remove(room);
        room.setHotel(null);

        hotelRepository.save(hotel);
    }

}
