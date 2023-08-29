package com.yamacbayin.medjourney.model.requestdto;

import lombok.Data;

@Data
public class BaseFilterRequestDTO {
    private int pageNumber;
    private int pageSize;
    private SortRequestDTO sortRequestDTO;
    //TODO: implement
    //private List<SearchCriteria> filters;
}
