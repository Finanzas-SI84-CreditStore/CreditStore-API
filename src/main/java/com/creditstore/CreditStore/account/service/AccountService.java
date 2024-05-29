package com.creditstore.CreditStore.account.service;

import com.creditstore.CreditStore.account.dto.AccountRequest;
import com.creditstore.CreditStore.account.dto.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse create(AccountRequest accountRequest);
    AccountResponse getById(Integer id);
    List<AccountResponse> getAll();
    AccountResponse update(Integer id, AccountRequest accountRequest);
    void delete(Integer id);
}
