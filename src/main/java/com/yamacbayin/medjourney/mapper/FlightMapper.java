package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.FlightEntity;
import com.yamacbayin.medjourney.model.requestdto.FlightRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightMapper extends IBaseMapper<FlightEntity, FlightResponseDTO, FlightRequestDTO> {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);


}
