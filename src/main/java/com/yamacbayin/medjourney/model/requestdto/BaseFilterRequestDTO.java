package com.yamacbayin.medjourney.model.requestdto;

import com.yamacbayin.medjourney.util.SearchCriteria;
import lombok.Data;

import java.util.List;

@Data
public class BaseFilterRequestDTO {
    private int pageNumber;
    private int pageSize;
    private SortRequestDTO sortRequestDTO;
    private List<SearchCriteria> filters;
}
