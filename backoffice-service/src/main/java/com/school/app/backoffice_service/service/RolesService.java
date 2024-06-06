package com.school.app.backoffice_service.service;

import com.school.app.backoffice_service.dto.response.RolesResponse;

import java.util.List;

public interface RolesService {

    List<RolesResponse> dropdownRoles();
}
