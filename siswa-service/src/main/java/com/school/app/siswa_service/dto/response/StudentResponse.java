package com.school.app.siswa_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {

    private String id;
    private String gender;
    private String fullname;
    private String email;
    private String phoneNo;
    private String address;
    private String avatar;
}
