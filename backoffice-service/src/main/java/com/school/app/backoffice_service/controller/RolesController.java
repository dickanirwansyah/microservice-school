package com.school.app.backoffice_service.controller;

import com.school.app.backoffice_service.dto.response.RestResponse;
import com.school.app.backoffice_service.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/backoffice-service")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @GetMapping(value = "/v1/dropdown-roles")
    public ResponseEntity<RestResponse> dropdown(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(rolesService.dropdownRoles()));
    }

    @GetMapping(value = "/v1/fetch-roles-byid/{id}")
    public ResponseEntity<RestResponse> fetchRolesById(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(rolesService.findByRolesById(id)));
    }
}
