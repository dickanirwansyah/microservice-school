package com.school.app.backoffice_service.exception;

import lombok.Data;

@Data
public class CustomMessageException extends RuntimeException{

    private int status;

    public CustomMessageException(String message, int status){
        super(message);
        this.status = status;
    }
}
