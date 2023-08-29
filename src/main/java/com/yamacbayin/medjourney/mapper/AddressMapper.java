package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.AddressEntity;
import com.yamacbayin.medjourney.model.requestdto.AddressRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AddressResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper extends IBaseMapper<AddressEntity, AddressResponseDTO, AddressRequestDTO> {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

}
