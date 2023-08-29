package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.PatientEntity;
import com.yamacbayin.medjourney.model.requestdto.PatientRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.PatientResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper extends IBaseMapper<PatientEntity, PatientResponseDTO, PatientRequestDTO> {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

}
