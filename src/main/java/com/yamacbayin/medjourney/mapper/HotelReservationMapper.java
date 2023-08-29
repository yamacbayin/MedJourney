package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.HotelReservationEntity;
import com.yamacbayin.medjourney.model.requestdto.HotelReservationRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelReservationResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelReservationMapper extends IBaseMapper<
        HotelReservationEntity, HotelReservationResponseDTO, HotelReservationRequestDTO> {

    HotelReservationMapper INSTANCE = Mappers.getMapper(HotelReservationMapper.class);

    @Override
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "guest", ignore = true)
    HotelReservationEntity requestDtoToEntity(HotelReservationRequestDTO hotelReservationRequestDTO);

    @Override
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "guest", ignore = true)
    HotelReservationEntity updateEntityFromRequestDTO(HotelReservationRequestDTO hotelReservationRequestDTO,
                                                      @MappingTarget HotelReservationEntity entity);

}
