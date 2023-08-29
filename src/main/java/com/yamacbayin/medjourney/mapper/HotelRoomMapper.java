package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.HotelRoomEntity;
import com.yamacbayin.medjourney.model.requestdto.HotelRoomRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelRoomResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelRoomMapper extends IBaseMapper<HotelRoomEntity, HotelRoomResponseDTO, HotelRoomRequestDTO> {

    HotelRoomMapper INSTANCE = Mappers.getMapper(HotelRoomMapper.class);

    @Override
    @Mapping(target = "hotel", ignore = true)
    HotelRoomEntity requestDtoToEntity(HotelRoomRequestDTO hotelRoomRequestDTO);

    @Override
    @Mapping(target = "hotel", ignore = true)
    HotelRoomEntity updateEntityFromRequestDTO(HotelRoomRequestDTO hotelRoomRequestDTO,
                                               @MappingTarget HotelRoomEntity entity);
}
