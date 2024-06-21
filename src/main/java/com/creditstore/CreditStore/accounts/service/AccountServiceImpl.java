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
        BigDecimal ValorCompra = accountRequest.getValorCompra();

        /*if (availableBalance.compareTo(ValorCompra) < 0) {
            throw new ServiceException(Error.CREDIT_LINE_EXCEEDED);
        }*/

        Account account = fromRequest(accountRequest, client);
        //TODO: SE DEBE CALCULAR EL VALOR RESTANTE
        account = accountRepository.save(account);
        //TODO: SE DEBE HACER PRUEBAS
        client.setAvailableBalance(client.getAvailableBalance() - accountRequest.getValorCompra().doubleValue());
        client.setDebt(client.getDebt() + accountRequest.getValorCompra().doubleValue());
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

        if(Double.compare(client.getAvailableBalance(), accountRequest.getValorCompra().doubleValue()) < 0){
            throw new ServiceException(Error.CREDIT_LINE_EXCEEDED);
        }

        Account account = fromRequest(accountRequest, client);
        account.setId(id);
        //TODO: SE DEBE AGREGAR LÓGICA DE PAGO DEUDA
        account = accountRepository.save(account);

        client.setAvailableBalance(client.getAvailableBalance() - accountRequest.getValorCompra().doubleValue());
        client.setDebt(client.getDebt() + accountRequest.getValorCompra().doubleValue());
        clientRepository.save(client);

        return toResponse(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    private Account fromRequest(AccountRequest accountRequest, Client client) {
        return Account.builder()
                .ValorCompra(accountRequest.getValorCompra())
                .TipoTasa(accountRequest.getTipoTasa())
                .CapitalizacionTasa(accountRequest.getCapitalizacionTasa())
                .ValorTasa(accountRequest.getValorTasa())
                .TipoCredito(accountRequest.getTipoCredito())
                .NumeroCuotas(accountRequest.getNumeroCuotas())
                .PlazoGracia(accountRequest.getPlazoGracia())
                .PeriodoGracia(accountRequest.getPeriodoGracia())
                .paymentDate(accountRequest.getPaymentDate()) // Añadido
                .client(client)
                .build();
    }

    private AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setValorCompra(account.getValorCompra());
        response.setTipoTasa(account.getTipoTasa());
        response.setCapitalizacionTasa(account.getCapitalizacionTasa());
        response.setValorTasa(account.getValorTasa());
        response.setTipoCredito(account.getTipoCredito());
        response.setNumeroCuotas(account.getNumeroCuotas());
        response.setPlazoGracia(account.getPlazoGracia());
        response.setPeriodoGracia(account.getPeriodoGracia());
        response.setPaymentDate(account.getPaymentDate()); // Añadido
        return response;
    }

    private void validateAccountRequest(AccountRequest accountRequest) {
        if (accountRequest.getValorCompra() == null) {
            throw new ServiceException(Error.PURCHASE_VALUE_REQUIRED);
        }
        if (accountRequest.getTipoTasa() == null || accountRequest.getTipoTasa().isEmpty()) {
            throw new ServiceException(Error.INTEREST_TYPE_REQUIRED);
        }
        if (accountRequest.getCapitalizacionTasa() == null || accountRequest.getCapitalizacionTasa().isEmpty()) {
            throw new ServiceException(Error.CAPITALIZATION_PERIOD_REQUIRED);
        }
        if (accountRequest.getValorTasa() == null) {
            throw new ServiceException(Error.INTEREST_RATE_REQUIRED);
        }
        if (accountRequest.getTipoCredito() == null || accountRequest.getTipoCredito().isEmpty()) {
            throw new ServiceException(Error.CREDIT_TYPE_REQUIRED);
        }
        if (accountRequest.getNumeroCuotas() == null) {
            throw new ServiceException(Error.INSTALLMENT_COUNT_REQUIRED);
        }
        if (accountRequest.getPlazoGracia() == null) {
            throw new ServiceException(Error.GRACE_PERIOD_REQUIRED);
        }
        if (accountRequest.getPeriodoGracia() == null) {
            throw new ServiceException(Error.GRACE_PERIOD_LENGTH_REQUIRED);
        }
        if (accountRequest.getPaymentDate() == null) {
            throw new ServiceException(Error.PAYMENT_DATE_REQUIRED); // Añadido
        }
        if (accountRequest.getClientId() == null) {
            throw new ServiceException(Error.CLIENT_NOT_FOUND);
        }
    }
}