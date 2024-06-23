package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.accounts.entity.Pay;
import com.creditstore.CreditStore.accounts.model.PayRequest;
import com.creditstore.CreditStore.accounts.model.PayResponse;
import com.creditstore.CreditStore.accounts.repository.AccountRepository;
import com.creditstore.CreditStore.accounts.repository.DatosSalidaRepository;
import com.creditstore.CreditStore.accounts.repository.PayRepository;
import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.clients.repository.ClientRepository;
import com.creditstore.CreditStore.shared.formulas.DatosSalida;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayRepository payRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DatosSalidaRepository datosSalidaRepository;

    @Autowired
    private ClientRepository clientRepository;



    @Override
    public PayResponse create(PayRequest payRequest, Integer accountId) {

        // Variable para la fecha del día de hoy
        LocalDate today = LocalDate.now();

        Pay pay = fromRequest(payRequest, accountId);
        pay = payRepository.save(pay);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceException(Error.ACCOUNT_NOT_FOUND));
        List<DatosSalida> datosSalida = datosSalidaRepository.findAllByAccount_Id(account.getId());

        Client client = account.getClient();


        for (DatosSalida dato : datosSalida) {
            //logica condiciomal
            if (pay.getAmount() <= 0) {
                break;
            }
            if (dato.getEstado().equals("POR_PAGAR") ) {
                dato.setEstado("PAGADO");
                break;
            }
        }

        datosSalidaRepository.saveAll(datosSalida);
        // Calcular la deuda total del cliente sumando los saldos finales de todas las cuentas del cliente
        Double deudaTotalCredito = 0.0;
        List<Account> accounts = accountRepository.findAllByClientId(client.getId());
        for (Account acc : accounts) {
            List<DatosSalida> datosSalidaCuenta = datosSalidaRepository.findAllByAccount_Id(acc.getId());
            for (DatosSalida dato : datosSalidaCuenta) {
                LocalDate fechaDato = LocalDate.parse(dato.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (dato.getEstado().equals("POR_PAGAR") && fechaDato.getMonthValue() == today.getMonthValue() && fechaDato.getYear() == today.getYear()) {
                    deudaTotalCredito += dato.getSaldoFinal();
                }
            }
        }

        // Actualizar la deuda y la línea de crédito disponible del cliente
        client.setDebt(deudaTotalCredito);
        client.setAvailableBalance(client.getCreditLine() - deudaTotalCredito);

        clientRepository.save(client);

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
