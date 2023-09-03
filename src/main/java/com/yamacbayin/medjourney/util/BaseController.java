package com.yamacbayin.medjourney.util;

import com.yamacbayin.medjourney.model.requestdto.BaseFilterRequestDTO;
import com.yamacbayin.medjourney.model.responsedto.PageResponseDTO;
import com.yamacbayin.medjourney.util.dbutil.BaseEntity;
import com.yamacbayin.medjourney.util.dbutil.IBaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class BaseController<
        Entity extends BaseEntity,
        ResponseDTO extends BaseResponseDTO,
        RequestDTO,
        Repository extends IBaseRepository<Entity>,
        Mapper extends IBaseMapper<Entity, ResponseDTO, RequestDTO>,
        Specification extends BaseSpecification<Entity>,
        Service extends BaseService<Entity, ResponseDTO, RequestDTO, Repository, Mapper, Specification>> {

    protected abstract Service getService();

/*    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAll() {
        return new ResponseEntity<>(getService().getAll(), HttpStatus.OK);
    }*/

    @PostMapping("get-all")
    public ResponseEntity<PageResponseDTO<ResponseDTO>> getAll(
            @RequestBody BaseFilterRequestDTO baseFilterRequestDTO) {
        return new ResponseEntity<>(getService().getAll(baseFilterRequestDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO = getService().save(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<ResponseDTO> getByUuid(@PathVariable UUID uuid) {
        ResponseDTO responseDTO = getService().getByUuid(uuid);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Boolean> deleteByUuid(@PathVariable UUID uuid) {
        Boolean isDeleted = getService().deleteByUuid(uuid);
        if (Boolean.TRUE.equals(isDeleted)) {
            return new ResponseEntity<>(isDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(isDeleted, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"update"})
    public ResponseEntity<ResponseDTO> update(@RequestParam UUID uuid, RequestDTO requestDTO) {
        ResponseDTO responseDTO = getService().update(uuid, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
