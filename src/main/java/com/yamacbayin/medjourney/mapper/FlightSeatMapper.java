package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.FlightSeatEntity;
import com.yamacbayin.medjourney.model.requestdto.FlightSeatRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightSeatResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightSeatMapper extends IBaseMapper<FlightSeatEntity, FlightSeatResponseDTO, FlightSeatRequestDTO> {

    FlightSeatMapper INSTANCE = Mappers.getMapper(FlightSeatMapper.class);

    @Override
    @Mapping(target = "flight", ignore = true)
    FlightSeatEntity requestDtoToEntity(FlightSeatRequestDTO flightSeatRequestDTO);

    @Override
    @Mapping(target = "flight", ignore = true)
    FlightSeatEntity updateEntityFromRequestDTO(FlightSeatRequestDTO flightSeatRequestDTO,
                                                @MappingTarget FlightSeatEntity entity);

}
