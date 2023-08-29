package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.DoctorEntity;
import com.yamacbayin.medjourney.model.requestdto.DoctorRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.DoctorResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DoctorMapper extends IBaseMapper<DoctorEntity, DoctorResponseDTO, DoctorRequestDTO> {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Override
    @Mapping(target = "hospital", ignore = true)
    DoctorEntity requestDtoToEntity(DoctorRequestDTO doctorRequestDTO);

    @Override
    @Mapping(target = "hospital", ignore = true)
    DoctorEntity updateEntityFromRequestDTO(DoctorRequestDTO doctorRequestDTO, @MappingTarget DoctorEntity entity);

}
