package com.school.app.siswa_service.feign.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.app.siswa_service.dto.response.RestResponse;
import com.school.app.siswa_service.exception.CustomMessageException;
import com.school.app.siswa_service.feign.feignclient.BackofficeFeign;
import com.school.app.siswa_service.feign.model.RolesResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BackofficeService {

    private final BackofficeFeign backofficeFeign;

    @Value("${feignClient.backoffice-service.rolesIdSiswa}")
    private String rolesIdSiswa;

    private ObjectMapper objectMapper;

    public RolesResponse fetchRolesById(){
        try{
            objectMapper = new ObjectMapper();
            ResponseEntity<RestResponse> response = backofficeFeign.fetchRolesById(rolesIdSiswa);
            return objectMapper.readValue(
                    objectMapper.writeValueAsString(response.getBody().getData()),
                    RolesResponse.class);
        }catch (JsonProcessingException e){
            log.error("json processing error because failed parse json = [{}]",e.getMessage());
            throw new CustomMessageException("terjadi kesalahan dalam memproses data", HttpStatus.NOT_ACCEPTABLE.value());
        }catch (FeignException e){
            log.error("feign client error because backoffice notfound = [{}]",e.getMessage());
            log.error("error ",e.getCause());
            throw new CustomMessageException("terjadi kesalahan dalam memproses data", HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
}
