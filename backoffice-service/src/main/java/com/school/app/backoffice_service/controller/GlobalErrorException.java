package com.school.app.backoffice_service.controller;


import com.school.app.backoffice_service.dto.response.RestResponse;
import com.school.app.backoffice_service.exception.CustomMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorException {

    @ExceptionHandler(CustomMessageException.class)
    public ResponseEntity<RestResponse> errorNotfound(CustomMessageException e){
        return ResponseEntity.status(e.getStatus())
                .body(RestResponse.isFail(e.getStatus(), e.getMessage()));
    }
}
