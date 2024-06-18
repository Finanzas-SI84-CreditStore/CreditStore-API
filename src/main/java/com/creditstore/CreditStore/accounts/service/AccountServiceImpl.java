package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.model.AccountRequest;
import com.creditstore.CreditStore.accounts.model.AccountResponse;
import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.accounts.repository.AccountRepository;
import com.creditstore.CreditStore.clients.repository.ClientRepository;
import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;



    @Override
    public AccountResponse create(AccountRequest accountRequest) {
        validateAccountRequest(accountRequest);

        Client client = clientRepository.findById(accountRequest.getClientId())
                .orElseThrow(() -> new ServiceException(Error.CLIENT_NOT_FOUND));
        //límite de credito
        BigDecimal availableBalance = BigDecimal.valueOf(client.getAvailableBalance());
        BigDecimal purchaseValue = accountRequest.getPurchaseValue();

        if (availableBalance.compareTo(purchaseValue) < 0) {
            throw new ServiceException(Error.CREDIT_LINE_EXCEEDED);
        }

        Account account = fromRequest(accountRequest, client);
        //TODO: SE DEBE CALCULAR EL VALOR RESTANTE
        account = accountRepository.save(account);
        //TODO: SE DEBE HACER PRUEBAS
        client.setAvailableBalance(client.getAvailableBalance() - accountRequest.getPurchaseValue().doubleValue());
        client.setDebt(client.getDebt() + accountRequest.getPurchaseValue().doubleValue());
        clientRepository.save(client);
        return toResponse(account);
    }

    @Override
    public AccountResponse getById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(this::toResponse).orElse(null);
    }

    @Override
    public List<AccountResponse> getAll(UUID clientId) {
        List<Account> accounts;
        if (clientId != null) {
            accounts = accountRepository.findAllByClientId(clientId);
        } else {
            accounts = accountRepository.findAll();
        }
        return accounts.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public AccountResponse update(Integer id, AccountRequest accountRequest) {
        validateAccountRequest(accountRequest);

        Client client = clientRepository.findById(accountRequest.getClientId())
                .orElseThrow(() -> new ServiceException(Error.CLIENT_NOT_FOUND));

        if(Double.compare(client.getAvailableBalance(), accountRequest.getPurchaseValue().doubleValue()) < 0){
            throw new ServiceException(Error.CREDIT_LINE_EXCEEDED);
        }

        Account account = fromRequest(accountRequest, client);
        account.setId(id);
        //TODO: SE DEBE AGREGAR LÓGICA DE PAGO DEUDA
        account = accountRepository.save(account);

        client.setAvailableBalance(client.getAvailableBalance() - accountRequest.getPurchaseValue().doubleValue());
        client.setDebt(client.getDebt() + accountRequest.getPurchaseValue().doubleValue());
        clientRepository.save(client);

        return toResponse(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    private Account fromRequest(AccountRequest accountRequest, Client client) {
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
                .client(client)
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

    private void validateAccountRequest(AccountRequest accountRequest) {
        if (accountRequest.getPurchaseValue() == null) {
            throw new ServiceException(Error.PURCHASE_VALUE_REQUIRED);
        }
        if (accountRequest.getInterestType() == null || accountRequest.getInterestType().isEmpty()) {
            throw new ServiceException(Error.INTEREST_TYPE_REQUIRED);
        }
        if (accountRequest.getCapitalizationPeriod() == null || accountRequest.getCapitalizationPeriod().isEmpty()) {
            throw new ServiceException(Error.CAPITALIZATION_PERIOD_REQUIRED);
        }
        if (accountRequest.getInterestPeriod() == null) {
            throw new ServiceException(Error.INTEREST_PERIOD_REQUIRED);
        }
        if (accountRequest.getInterestRate() == null) {
            throw new ServiceException(Error.INTEREST_RATE_REQUIRED);
        }
        if (accountRequest.getCreditType() == null || accountRequest.getCreditType().isEmpty()) {
            throw new ServiceException(Error.CREDIT_TYPE_REQUIRED);
        }
        if (accountRequest.getInstallmentCount() == null) {
            throw new ServiceException(Error.INSTALLMENT_COUNT_REQUIRED);
        }
        if (accountRequest.getGracePeriod() == null) {
            throw new ServiceException(Error.GRACE_PERIOD_REQUIRED);
        }
        if (accountRequest.getGracePeriodLength() == null) {
            throw new ServiceException(Error.GRACE_PERIOD_LENGTH_REQUIRED);
        }
        if (accountRequest.getClientId() == null) {
            throw new ServiceException(Error.CLIENT_NOT_FOUND);
        }
    }
}
