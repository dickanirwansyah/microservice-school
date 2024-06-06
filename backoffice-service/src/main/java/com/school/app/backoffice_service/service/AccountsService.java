package com.school.app.backoffice_service.service;

import com.school.app.backoffice_service.dto.request.AccountsRequest;
import com.school.app.backoffice_service.dto.request.SpecificationRequest;
import com.school.app.backoffice_service.dto.response.AccountsResponse;
import com.school.app.backoffice_service.dto.response.PageResponse;

public interface AccountsService {

    AccountsResponse save(AccountsRequest request);
    AccountsResponse update(AccountsRequest request);
    AccountsResponse find(String id);
    AccountsResponse delete(String id);
    PageResponse search(SpecificationRequest request);

}
