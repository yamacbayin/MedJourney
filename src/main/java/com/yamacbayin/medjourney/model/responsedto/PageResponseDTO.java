package com.yamacbayin.medjourney.model.responsedto;

import com.yamacbayin.medjourney.util.BaseResponseDTO;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class PageResponseDTO<ResponseDTO extends BaseResponseDTO> {
    private int number;
    private int size;
    private int totalPages;
    private Sort sort;
    private Long totalElements;
    private boolean hasContent;
    private List<ResponseDTO> content;
}
