package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.HospitalEntity;
import com.yamacbayin.medjourney.model.requestdto.HospitalRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.HospitalResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HospitalMapper extends IBaseMapper<HospitalEntity, HospitalResponseDTO, HospitalRequestDTO> {

    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

}
