package com.yamacbayin.medjourney.util;

import com.yamacbayin.medjourney.model.responsedto.PageResponseDTO;
import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBaseMapper<
        Entity extends BaseEntity,
        ResponseDTO extends BaseResponseDTO,
        RequestDTO> {

    Entity requestDtoToEntity(RequestDTO requestDTO);

    ResponseDTO entityToResponseDto(Entity entity);

    List<ResponseDTO> entityListToResponseDtoList(List<Entity> entityList);

    Entity updateEntityFromRequestDTO(RequestDTO requestDTO, @MappingTarget Entity entity);

    PageResponseDTO<ResponseDTO> pageEntityToPageResponseDTO(Page<Entity> entityPage);

}
