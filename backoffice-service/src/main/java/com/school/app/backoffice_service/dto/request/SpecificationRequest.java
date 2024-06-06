package com.school.app.backoffice_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationRequest {

    private Specification specification;
    private Pageable pageable;
}
