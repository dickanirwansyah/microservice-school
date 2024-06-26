package com.school.app.backoffice_service.service.impl;

import com.school.app.backoffice_service.dto.response.RolesResponse;
import com.school.app.backoffice_service.exception.CustomMessageException;
import com.school.app.backoffice_service.repository.RolesRepository;
import com.school.app.backoffice_service.service.RolesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public List<RolesResponse> dropdownRoles() {
        return rolesRepository.findAll()
                .stream().map(roles -> RolesResponse.builder()
                        .id(roles.getId())
                        .name(roles.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public RolesResponse findByRolesById(String rolesId) {
        log.info("execute find roles by id = [{}]",rolesId);
        return rolesRepository.findById(rolesId)
                .map(currentRoles -> RolesResponse.builder()
                        .id(currentRoles.getId()).name(currentRoles.getName()).build())
                .orElseThrow(() -> new CustomMessageException("maaf roles yang anda cari tidak ditemukan", HttpStatus.NOT_FOUND.value()));
    }
}
