package com.school.app.siswa_service.controller;

import com.school.app.siswa_service.dto.request.SearchRequest;
import com.school.app.siswa_service.dto.request.SpecificationRequest;
import com.school.app.siswa_service.dto.request.StudentRequest;
import com.school.app.siswa_service.dto.response.RestResponse;
import com.school.app.siswa_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/siswa-service/")
@RequiredArgsConstructor
public class StudentController extends BaseController{

    private final StudentService studentService;

    @PostMapping(value = "/v1/save")
    public ResponseEntity<RestResponse> save(@RequestBody StudentRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(studentService.save(request)));
    }

    @PutMapping(value = "/v1/update")
    public ResponseEntity<RestResponse> update(@RequestBody StudentRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(studentService.update(request)));
    }

    @DeleteMapping(value = "/v1/delete/{id}")
    public ResponseEntity<RestResponse> update(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(studentService.delete(id)));
    }

    @GetMapping(value = "/v1/find/{id}")
    public ResponseEntity<RestResponse> find(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(studentService.find(id)));
    }

    @GetMapping(value = "/v1/search")
    public ResponseEntity<RestResponse> search(SearchRequest request){
        return getPageableResponse(studentService.search(
                SpecificationRequest.builder()
                        .pageable(getCommonPageable(request))
                        .specification(getCommonSpecs(new String[]{"fullname","email","address"},0,request))
                        .build()).getPage());
    }

    @PutMapping(value = "/v1/upload-avatar")
    public ResponseEntity<RestResponse> uploadAvatar(@RequestBody StudentRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.isOk(studentService
                        .uploadAvatar(request)));
    }
}
