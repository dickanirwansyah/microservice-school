package com.school.app.backoffice_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {

    private String message;
    private Integer statusCode;
    private Object data;

    public static final RestResponse isOk(Object data){
        return RestResponse.builder()
                .data(data)
                .message("Ok")
                .statusCode(200)
                .build();
    }

    public static final RestResponse isFail(Integer statusCode,String message){
        return RestResponse.builder()
                .data(null)
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}
