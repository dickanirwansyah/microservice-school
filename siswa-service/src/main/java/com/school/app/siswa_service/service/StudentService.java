package com.school.app.siswa_service.service;

import com.school.app.siswa_service.dto.request.SpecificationRequest;
import com.school.app.siswa_service.dto.request.StudentRequest;
import com.school.app.siswa_service.dto.response.PageResponse;
import com.school.app.siswa_service.dto.response.StudentResponse;

public interface StudentService {

    StudentResponse save(StudentRequest request);
    StudentResponse update(StudentRequest request);
    StudentResponse delete(String id);
    StudentResponse uploadAvatar(StudentRequest request);
    PageResponse search(SpecificationRequest request);
    StudentResponse find(String id);
}
