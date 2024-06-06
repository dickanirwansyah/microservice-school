package com.school.app.backoffice_service.service;

import com.school.app.backoffice_service.dto.request.AuthenticationRequest;
import com.school.app.backoffice_service.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);
    AuthenticationResponse checkToken(String token);
}
