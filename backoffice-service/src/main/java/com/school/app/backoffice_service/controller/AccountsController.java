package com.school.app.backoffice_service.controller;

import com.school.app.backoffice_service.dto.request.AccountsRequest;
import com.school.app.backoffice_service.dto.request.SearchRequest;
import com.school.app.backoffice_service.dto.request.SpecificationRequest;
import com.school.app.backoffice_service.dto.response.RestResponse;
import com.school.app.backoffice_service.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/backoffice-service")
@RequiredArgsConstructor
public class AccountsController extends BaseController{

    private final AccountsService accountsService;

    @PostMapping(value = "/v1/save")
    public ResponseEntity<RestResponse> save(@RequestBody AccountsRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(accountsService.save(request)));
    }

    @PutMapping(value = "/v1/update")
    public ResponseEntity<RestResponse> update(@RequestBody AccountsRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(accountsService.update(request)));
    }

    @DeleteMapping(value = "/v1/delete/{id}")
    public ResponseEntity<RestResponse> update(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(accountsService.delete(id)));
    }

    @DeleteMapping(value = "/v1/find/{id}")
    public ResponseEntity<RestResponse> find(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(accountsService.find(id)));
    }

    @GetMapping(value = "/v1/search")
    public ResponseEntity<RestResponse> search(SearchRequest request){
        return getPageableResponse(accountsService.search(
                SpecificationRequest.builder()
                        .pageable(getCommonPageable(request))
                        .specification(getCommonSpecs(new String[]{"fullname","email"},0,request))
                        .build()).getPage());
    }
}
