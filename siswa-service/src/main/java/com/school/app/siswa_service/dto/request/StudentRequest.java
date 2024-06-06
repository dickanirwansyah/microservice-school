package com.school.app.siswa_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String id;
    private String gender;
    private String fullname;
    private String email;
    private String phoneNo;
    private String address;
    private String avatar;
    private String dob;
}
