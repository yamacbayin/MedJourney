package com.yamacbayin.medjourney.util;

import com.yamacbayin.medjourney.exception.InvalidUuidException;
import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public abstract class BaseService<
        Entity extends BaseEntity,
        ResponseDTO extends BaseResponseDTO,
        RequestDTO,
        Repository extends IBaseRepository<Entity>,
        Mapper extends IBaseMapper<Entity, ResponseDTO, RequestDTO>> {

    private static final String UUID_ERROR_MESSAGE = "UUID is not valid.";

    protected abstract Mapper getMapper();

    protected abstract Repository getRepository();

    public Entity getEntityByUuid(UUID uuid) {
        return getRepository().findByUuid(uuid).orElse(null);
    }

    public ResponseDTO getByUuid(UUID uuid) {
        Entity entity = getRepository().findByUuid(uuid).orElse(null);
        if (entity != null) {
            return getMapper().entityToResponseDto(entity);
        }
        throw new InvalidUuidException(UUID_ERROR_MESSAGE);
    }

    public List<ResponseDTO> getAll() {
        List<Entity> entityList = getRepository().findAll();
        return getMapper().entityListToResponseDtoList(entityList);
    }

/*    public PageResponseDTO<ResponseDTO> getAll(BaseFilterRequestDTO baseFilterRequestDTO) {
        Pageable pageable;
        if (baseFilterRequestDTO.getSortRequestDTO() != null) {
            if (baseFilterRequestDTO.getSortRequestDTO().getDirection() == Sort.Direction.ASC) {
                pageable = PageRequest.of(baseFilterRequestDTO.getPageNumber(),baseFilterRequestDTO.getPageSize(),
                        Sort.by(baseFilterRequestDTO.getSortRequestDTO().getColumnName()).ascending());
            } else {
                pageable = PageRequest.of(baseFilterRequestDTO.getPageNumber(),baseFilterRequestDTO.getPageSize(),
                        Sort.by(baseFilterRequestDTO.getSortRequestDTO().getColumnName()).descending());
            }
        } else {
            pageable = PageRequest.of(baseFilterRequestDTO.getPageNumber(),baseFilterRequestDTO.getPageSize(),
                    Sort.by("id").ascending());
        }

        //getSpecification().setCriteriaList(baseFilterRequestDTO.getFilters());

        //Page<Entity> entityPage = getRepository().findAll(getSpecification(),pageable);
        Page<Entity> entityPage = getRepository().findAll(pageable);
        return getMapper().pageEntityToPageResponseDTO(entityPage);
    }*/


    @Transactional
    public ResponseDTO save(RequestDTO requestDTO) {
        return getMapper()
                .entityToResponseDto(getRepository()
                        .save(getMapper().requestDtoToEntity(requestDTO)));
    }

    @Transactional
    public ResponseDTO update(UUID uuid, RequestDTO requestDTO) {
        Entity entity = getRepository().findByUuid(uuid).orElse(null);
        if (entity == null) {
            throw new InvalidUuidException(UUID_ERROR_MESSAGE);
        }
        return getMapper()
                .entityToResponseDto(getRepository()
                        .save(getMapper().updateEntityFromRequestDTO(requestDTO, entity)));
    }

    @Transactional
    public Boolean deleteByUuid(UUID uuid) {
        Entity entity = getRepository().findByUuid(uuid).orElse(null);
        if (entity != null) {
            getRepository().delete(entity);
            return true;
        }
        throw new InvalidUuidException(UUID_ERROR_MESSAGE);
    }

}
