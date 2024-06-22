package com.school.app.siswa_service.feign.feignclient;

import com.school.app.siswa_service.dto.response.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feignClient.backoffice-service.host}")
public interface BackofficeFeign {

    @GetMapping(value = "${feignClient.backoffice-service.fetchRolesById}")
    ResponseEntity<RestResponse> fetchRolesById(@PathVariable("id")String id);
}
