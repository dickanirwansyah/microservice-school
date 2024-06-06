package com.school.app.backoffice_service.dto.response;

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
public class AccountsResponse {

    private String id;
    private String fullname;

    private String rolesId;
    private String rolesName;

    private String email;

    private String password;

    private String dob;


}
