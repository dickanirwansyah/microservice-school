package com.school.app.backoffice_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsRequest {

    private String id;
    private String fullname;
    private String rolesId;
    private String email;
    private String password;
    private String dob;
}
