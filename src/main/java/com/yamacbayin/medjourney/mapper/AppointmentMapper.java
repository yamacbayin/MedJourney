package com.yamacbayin.medjourney.mapper;

import com.yamacbayin.medjourney.database.entity.AppointmentEntity;
import com.yamacbayin.medjourney.model.requestdto.AppointmentCreationRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.AppointmentResponseDTO;
import com.yamacbayin.medjourney.util.IBaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper extends IBaseMapper<
        AppointmentEntity, AppointmentResponseDTO, AppointmentCreationRequestDTO> {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Override
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    AppointmentEntity requestDtoToEntity(AppointmentCreationRequestDTO appointmentCreationRequestDTO);

    @Override
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    AppointmentEntity updateEntityFromRequestDTO(AppointmentCreationRequestDTO appointmentCreationRequestDTO,
                                                 @MappingTarget AppointmentEntity entity);
}
