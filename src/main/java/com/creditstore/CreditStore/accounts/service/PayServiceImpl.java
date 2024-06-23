package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.accounts.entity.Pay;
import com.creditstore.CreditStore.accounts.model.PayRequest;
import com.creditstore.CreditStore.accounts.model.PayResponse;
import com.creditstore.CreditStore.accounts.repository.AccountRepository;
import com.creditstore.CreditStore.accounts.repository.PayRepository;
import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayRepository payRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public PayResponse create(PayRequest payRequest, Integer accountId) {
        Pay pay = fromRequest(payRequest, accountId);
        pay = payRepository.save(pay);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceException(Error.ACCOUNT_NOT_FOUND));

        Client client = account.getClient();
        double newDebt = client.getDebt() - pay.getAmount();
        if (newDebt < 0) {
            newDebt = 0;
        }
        client.setDebt(newDebt);
        accountRepository.save(account);
        return toResponse(pay);
    }

    @Override
    public PayResponse getById(Integer id) {
        return payRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ServiceException(Error.PAY_NOT_FOUND));
    }

    @Override
    public List<PayResponse> getAllByAccountId(Integer accountId) {
        return payRepository.findAllByAccountId(accountId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PayResponse update(Integer id, PayRequest payRequest) {
        return null;
    }

    private Pay fromRequest(PayRequest payRequest, Integer accountId) {
        return Pay.builder()
                .amount(payRequest.getAmount())
                .date(payRequest.getDate())
                .account(accountRepository.findById(accountId).orElse(null))
                .build();
    }

    private PayResponse toResponse(Pay pay) {
        return PayResponse.builder()
                .id(pay.getId())
                .amount(pay.getAmount())
                .date(pay.getDate())
                .build();
    }
}
