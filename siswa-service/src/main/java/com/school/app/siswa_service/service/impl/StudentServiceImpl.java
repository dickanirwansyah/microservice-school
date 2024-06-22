package com.school.app.siswa_service.service.impl;

import com.school.app.siswa_service.dto.request.SpecificationRequest;
import com.school.app.siswa_service.dto.request.StudentRequest;
import com.school.app.siswa_service.dto.response.PageResponse;
import com.school.app.siswa_service.dto.response.StudentResponse;
import com.school.app.siswa_service.entity.Student;
import com.school.app.siswa_service.exception.CustomMessageException;
import com.school.app.siswa_service.feign.model.RolesResponse;
import com.school.app.siswa_service.feign.service.BackofficeService;
import com.school.app.siswa_service.helper.StudentHelper;
import com.school.app.siswa_service.repository.StudentRepository;
import com.school.app.siswa_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final BackofficeService backofficeService;

    @Override
    public StudentResponse save(StudentRequest request) {
        Student student = new Student();
        StudentResponse studentResponse = new StudentResponse();
        RolesResponse rolesResponse = backofficeService.fetchRolesById();

        student.setId(UUID.randomUUID().toString());
        student.setFullname(request.getFullname());
        student.setPhoneNo(request.getPhoneNo());
        student.setGender(request.getGender());
        student.setEmail(request.getEmail());
        student.setAddress(request.getAddress());
        student.setDob(StudentHelper.convertDob(request.getDob()));
        student.setAvatar(request.getAvatar());
        student.setDeleted(0);
        student.setRolesId(rolesResponse.getId());
        student.setRolesName(rolesResponse.getName());
        Student responseStudent = studentRepository.save(student);
        BeanUtils.copyProperties(responseStudent, studentResponse);
        return studentResponse;
    }

    @Override
    public StudentResponse update(StudentRequest request) {
        Optional<Student> currentStudent = studentRepository.findById(request.getId());
        StudentResponse studentResponse = new StudentResponse();
        if (!currentStudent.isEmpty()){
            currentStudent.get().setFullname(request.getFullname());
            currentStudent.get().setEmail(request.getEmail());
            currentStudent.get().setPhoneNo(request.getPhoneNo());
            currentStudent.get().setGender(request.getGender());
            currentStudent.get().setDob(StudentHelper.convertDob(request.getDob()));
            currentStudent.get().setAddress(request.getAddress());
            Student responseStudent = studentRepository.save(currentStudent.get());
            BeanUtils.copyProperties(responseStudent, studentResponse);
            return studentResponse;
        }
        throw new CustomMessageException("Update Gagal - Maaf Data Murid Tidak Ada", HttpStatus.SC_NOT_FOUND);
    }

    @Override
    public StudentResponse delete(String id) {
        Optional<Student> currentStudent = studentRepository.findById(id);
        StudentResponse studentResponse = new StudentResponse();
        if (!currentStudent.isEmpty()){
            currentStudent.get().setDeleted(1);
            Student responseStudent = studentRepository.save(currentStudent.get());
            BeanUtils.copyProperties(responseStudent, studentResponse);
            return studentResponse;
        }
        throw new CustomMessageException("Delete Gagal - Maaf Data Murid Tidak Ada", HttpStatus.SC_NOT_FOUND);
    }


    @Override
    public StudentResponse uploadAvatar(StudentRequest request) {
        Optional<Student> currentStudent = studentRepository.findById(request.getId());
        if (!currentStudent.isEmpty()){
            //upload base64 image
            currentStudent.get().setAvatar(request.getAvatar());
            Student responseStudent = studentRepository.save(currentStudent.get());
            return StudentResponse.builder()
                    .id(responseStudent.getId())
                    .fullname(responseStudent.getFullname())
                    .email(responseStudent.getEmail())
                    .build();
        }
        throw new CustomMessageException("Upload Avatar Gagal - Maaf Data Murid Tidak Ada",HttpStatus.SC_NOT_FOUND);
    }

    @Override
    public PageResponse search(SpecificationRequest request) {
        Page<Student> studentPage = studentRepository.findAll(request.getSpecification(), request.getPageable());
        return PageResponse.builder()
                .page(studentPage.isEmpty() ? null : studentPage)
                .build();
    }

    @Override
    public StudentResponse find(String id) {
        return studentRepository.findById(id)
                .map(student -> StudentResponse
                        .builder()
                        .id(student.getId())
                        .gender(student.getGender())
                        .phoneNo(student.getPhoneNo())
                        .address(student.getAddress())
                        .avatar(student.getAvatar())
                        .email(student.getEmail())
                        .fullname(student.getFullname())
                        .build())
                .orElseThrow(() -> new CustomMessageException("Find Id Siswa Gagal - Maaf Data Murid tidak ditemukan.",HttpStatus.SC_NOT_FOUND));
    }
}
