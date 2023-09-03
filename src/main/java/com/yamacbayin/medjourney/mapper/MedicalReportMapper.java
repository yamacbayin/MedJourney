package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.MedicalReportEntity;
import com.yamacbayin.medjourney.model.requestdto.MedicalReportRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.MedicalReportResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicalReportMapper extends IBaseMapper<
        MedicalReportEntity, MedicalReportResponseDTO, MedicalReportRequestDTO> {

    MedicalReportMapper INSTANCE = Mappers.getMapper(MedicalReportMapper.class);

    @Override
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    MedicalReportEntity requestDtoToEntity(MedicalReportRequestDTO medicalReportRequestDTO);

    @Override
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    MedicalReportEntity updateEntityFromRequestDTO(MedicalReportRequestDTO medicalReportRequestDTO,
                                                   @MappingTarget MedicalReportEntity entity);
}
