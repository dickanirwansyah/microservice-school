package com.school.app.siswa_service.controller;

import com.school.app.siswa_service.dto.request.SearchRequest;
import com.school.app.siswa_service.dto.response.RestResponse;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseController {

    //check header

    protected ResponseEntity<RestResponse> getPageableResponse(Page dataPage){
        RestResponse response;
        if(ObjectUtils.isEmpty(dataPage)){
            log.info("data pageable not found");
            response = RestResponse.builder()
                    .data(dataPage)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Data tidak ditemukan")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response = RestResponse.builder()
                .message("Ok")
                .statusCode(HttpStatus.OK.value())
                .data(dataPage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected Pageable getCommonPageable(SearchRequest request){
        int page = ObjectUtils.isEmpty(request.getStart())? 0 : request.getStart();
        int size = ObjectUtils.isEmpty(request.getLimit()) ? Integer.MAX_VALUE : request.getLimit();
        Boolean isAscending = StringUtils.isNotEmpty(request.getSort()) && request.getSort().equals("ASC");
        Sort.Direction sort = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        String sortBy = StringUtils.isEmpty(request.getSortBy()) ? "fullname" : request.getSortBy();
        return PageRequest.of(page, size, sort, sortBy);
    }

    protected Specification getCommonSpecs(String[] rootFields, Integer deleted, SearchRequest request){

        return Specification.where((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!ObjectUtils.isEmpty(deleted)){
                predicates.add(criteriaBuilder.equal(root.get("deleted"), deleted));
            }
            if (StringUtils.isNotEmpty(request.getTextSearch())){
                List<Predicate> predicateTexts = new ArrayList<>();
                for (String rootField : rootFields){
                    predicateTexts.add(criteriaBuilder.like(criteriaBuilder
                            .lower(root.get(rootField)), "%" + request.getTextSearch().toLowerCase() + "%"));
                }
                predicates.add(criteriaBuilder.or(predicateTexts.toArray(new Predicate[] {})));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        });
    }
}
