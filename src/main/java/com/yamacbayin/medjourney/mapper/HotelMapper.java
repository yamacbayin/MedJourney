package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.HotelEntity;
import com.yamacbayin.medjourney.model.requestdto.HotelRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HotelResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelMapper extends IBaseMapper<HotelEntity, HotelResponseDTO, HotelRequestDTO> {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

}
