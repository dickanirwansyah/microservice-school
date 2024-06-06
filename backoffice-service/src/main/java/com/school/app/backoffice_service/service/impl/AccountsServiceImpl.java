package com.school.app.backoffice_service.service.impl;

import com.school.app.backoffice_service.dto.request.AccountsRequest;
import com.school.app.backoffice_service.dto.request.SpecificationRequest;
import com.school.app.backoffice_service.dto.response.AccountsResponse;
import com.school.app.backoffice_service.dto.response.PageResponse;
import com.school.app.backoffice_service.entity.Accounts;
import com.school.app.backoffice_service.entity.Roles;
import com.school.app.backoffice_service.exception.CustomMessageException;
import com.school.app.backoffice_service.helper.BackofficeHelper;
import com.school.app.backoffice_service.repository.AccountsRepository;
import com.school.app.backoffice_service.repository.RolesRepository;
import com.school.app.backoffice_service.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final RolesRepository rolesRepository;

    @Override
    public AccountsResponse save(AccountsRequest request) {
        Optional<Roles> currentRoles = rolesRepository.findById(request.getRolesId());
        if (currentRoles.isPresent()){
            Accounts accounts = new Accounts();
            AccountsResponse accountsResponse = new AccountsResponse();
            BeanUtils.copyProperties(request,accounts);
            accounts.setId(UUID.randomUUID().toString());
            accounts.setDeleted(0);
            accounts.setDob(BackofficeHelper.convertDob(request.getDob()));
            Accounts responseAccounts = accountsRepository.save(accounts);
            BeanUtils.copyProperties(responseAccounts, accountsResponse);
            return accountsResponse;
        }
        throw new CustomMessageException("Penyimpanan account gagal, karena role tidak ditemukan !", HttpStatus.NOT_FOUND.value());
    }

    @Override
    public AccountsResponse update(AccountsRequest request) {
        AccountsResponse accountsResponse = new AccountsResponse();
        Optional<Accounts> currentAccount = accountsRepository.findById(request.getId());
        Optional<Roles> currentRoles = rolesRepository.findById(request.getRolesId());
        if (currentAccount.isPresent()){
            currentAccount.get().setFullname(request.getFullname());
            currentAccount.get().setEmail(request.getEmail());
            currentAccount.get().setDob(BackofficeHelper.convertDob(request.getDob()));
            if (currentRoles.isEmpty()){
                throw new CustomMessageException("Update account gagal, karena role tidak ditemukan !",HttpStatus.NOT_FOUND.value());
            }
            currentAccount.get().setRolesId(request.getRolesId());
            Accounts responseAccounts = accountsRepository.save(currentAccount.get());
            BeanUtils.copyProperties(responseAccounts, accountsResponse);
            return accountsResponse;
        }
        throw new CustomMessageException("Penyimpanan account gagal, karena account id tidak ditemukan !",HttpStatus.NOT_FOUND.value());
    }


    @Override
    public AccountsResponse find(String id) {
        return accountsRepository.findById(id)
                .map(accounts -> AccountsResponse.builder()
                        .id(accounts.getId())
                        .dob(accounts.getDob().toString())
                        .email(accounts.getEmail())
                        .fullname(accounts.getFullname())
                        .build())
                .orElseThrow(() -> new CustomMessageException("Find data accounts gagal, karena id tidak ditemukan !",HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public AccountsResponse delete(String id) {
        return accountsRepository.findById(id)
                .map(accounts -> {
                    AccountsResponse accountsResponse = new AccountsResponse();
                    accounts.setDeleted(1);
                    Accounts responseAccounts = accountsRepository.save(accounts);
                    BeanUtils.copyProperties(responseAccounts,accountsResponse);
                    return accountsResponse;
                }).orElseThrow(() -> new CustomMessageException("Delete data account gagal, karena id tidak ditemukan !",HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public PageResponse search(SpecificationRequest request) {
        return PageResponse.builder()
                .page(accountsRepository.findAll(request.getSpecification(), request.getPageable()))
                .build();
    }
}
