package com.school.app.backoffice_service.service.impl;

import com.school.app.backoffice_service.dto.request.AuthenticationRequest;
import com.school.app.backoffice_service.dto.response.AccountsResponse;
import com.school.app.backoffice_service.dto.response.AuthenticationResponse;
import com.school.app.backoffice_service.entity.Accounts;
import com.school.app.backoffice_service.exception.CustomMessageException;
import com.school.app.backoffice_service.helper.SecurityHelper;
import com.school.app.backoffice_service.repository.AccountsRepository;
import com.school.app.backoffice_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountsRepository accountsRepository;
    private final SecurityHelper securityHelper;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<Accounts> findAccounts = accountsRepository.findByEmail(request.getEmail());
        if (!findAccounts.isPresent()){
            log.info("email [{}] tidak ditemukan",request.getEmail());
            throw new CustomMessageException("Maaf email / password tidak ditemukan !", HttpStatus.UNAUTHORIZED.value());
        }
        if (!findAccounts.get().getPassword().equals(request.getPassword())){
            log.info("password tidak match");
            throw new CustomMessageException("Maaf email / password tidak ditemukan !",HttpStatus.UNAUTHORIZED.value());
        }
        AccountsResponse accountsResponse = new AccountsResponse();
        BeanUtils.copyProperties(findAccounts.get(), accountsResponse);
        String token = securityHelper.generateJwtToken(accountsResponse);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse checkToken(String token) {
        boolean checkToken = securityHelper.validateJwtToken(token);
        if (checkToken){
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        }
        throw new CustomMessageException("Session Expired !",HttpStatus.FORBIDDEN.value());
    }
}
