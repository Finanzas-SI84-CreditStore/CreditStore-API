package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.model.AccountRequest;
import com.creditstore.CreditStore.accounts.model.AccountResponse;
import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponse create(AccountRequest accountRequest) {
        Account account = fromRequest(accountRequest);
        account = accountRepository.save(account);
        return toResponse(account);
    }

    @Override
    public AccountResponse getById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(this::toResponse).orElse(null);
    }

    @Override
    public List<AccountResponse> getAll() {
        return accountRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public AccountResponse update(Integer id, AccountRequest accountRequest) {
        Account account = fromRequest(accountRequest);
        account.setId(id);
        account = accountRepository.save(account);
        return toResponse(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    private Account fromRequest(AccountRequest accountRequest) {
        return Account.builder()
                .purchaseValue(accountRequest.getPurchaseValue())
                .interestType(accountRequest.getInterestType())
                .capitalizationPeriod(accountRequest.getCapitalizationPeriod())
                .interestPeriod(accountRequest.getInterestPeriod())
                .interestRate(accountRequest.getInterestRate())
                .creditType(accountRequest.getCreditType())
                .installmentCount(accountRequest.getInstallmentCount())
                .gracePeriod(accountRequest.getGracePeriod())
                .gracePeriodLength(accountRequest.getGracePeriodLength())
                .build();
    }

    private AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setPurchaseValue(account.getPurchaseValue());
        response.setInterestType(account.getInterestType());
        response.setCapitalizationPeriod(account.getCapitalizationPeriod());
        response.setInterestPeriod(account.getInterestPeriod());
        response.setInterestRate(account.getInterestRate());
        response.setCreditType(account.getCreditType());
        response.setInstallmentCount(account.getInstallmentCount());
        response.setGracePeriod(account.getGracePeriod());
        response.setGracePeriodLength(account.getGracePeriodLength());
        return response;
    }
}
