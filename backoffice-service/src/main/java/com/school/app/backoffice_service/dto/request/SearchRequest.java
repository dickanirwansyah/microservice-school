package com.school.app.backoffice_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private String textSearch;
    private Integer start;
    private Integer limit;
    private String sortBy;
    private String sort;
}
