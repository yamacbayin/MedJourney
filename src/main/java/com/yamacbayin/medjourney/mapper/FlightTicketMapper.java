package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.FlightTicketEntity;
import com.yamacbayin.medjourney.model.requestdto.FlightTicketRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.FlightTicketResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightTicketMapper extends IBaseMapper<FlightTicketEntity,
        FlightTicketResponseDTO, FlightTicketRequestDTO> {

    FlightTicketMapper INSTANCE = Mappers.getMapper(FlightTicketMapper.class);

    @Override
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "seat", ignore = true)
    @Mapping(target = "passenger", ignore = true)
    FlightTicketEntity requestDtoToEntity(FlightTicketRequestDTO flightTicketRequestDTO);

    @Override
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "seat", ignore = true)
    @Mapping(target = "passenger", ignore = true)
    FlightTicketEntity updateEntityFromRequestDTO(FlightTicketRequestDTO flightTicketRequestDTO,
                                                  @MappingTarget FlightTicketEntity entity);
}
