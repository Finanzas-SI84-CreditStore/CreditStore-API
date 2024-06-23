package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.model.AccountRequest;
import com.creditstore.CreditStore.accounts.model.AccountResponse;
import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.clients.model.ClientDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponse create(AccountRequest accountRequest);
    AccountResponse getById(Integer id);
    List<Account> getAll(UUID clientId);
    AccountResponse update(Integer id, AccountRequest accountRequest);
    void delete(Integer id);
    ClientDto getClientDebt(Integer accountId); // Nuevo método para obtener la deuda del cliente
}
