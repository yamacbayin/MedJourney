package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.AirplaneEntity;
import com.yamacbayin.medjourney.model.requestdto.AirplaneRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AirplaneResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AirplaneMapper extends IBaseMapper<AirplaneEntity, AirplaneResponseDTO, AirplaneRequestDTO> {

    AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

}
