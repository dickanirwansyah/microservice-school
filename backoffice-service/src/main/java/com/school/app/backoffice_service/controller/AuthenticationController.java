package com.school.app.backoffice_service.controller;

import com.school.app.backoffice_service.dto.request.AuthenticationRequest;
import com.school.app.backoffice_service.dto.request.CheckSessionTokenRequest;
import com.school.app.backoffice_service.dto.response.RestResponse;
import com.school.app.backoffice_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/backoffice-service")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/v1/login")
    public ResponseEntity<RestResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(authenticationService.login(request)));
    }

    @PostMapping(value = "/v1/check-token")
    public ResponseEntity<RestResponse> checkToken(@RequestBody CheckSessionTokenRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(authenticationService.checkToken(request.getToken())));
    }
}
