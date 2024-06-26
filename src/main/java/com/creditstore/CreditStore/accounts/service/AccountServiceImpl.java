package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.model.AccountRequest;
import com.creditstore.CreditStore.accounts.model.AccountResponse;
import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.accounts.repository.AccountRepository;
import com.creditstore.CreditStore.accounts.repository.DatosSalidaRepository;
import com.creditstore.CreditStore.clients.repository.ClientRepository;
import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.clients.model.ClientDto;
import com.creditstore.CreditStore.shared.formulas.CalculadoraGrilla;
import com.creditstore.CreditStore.shared.formulas.DatosEntrada;
import com.creditstore.CreditStore.shared.formulas.DatosSalida;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DatosSalidaRepository datosSalidaRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public AccountResponse create(AccountRequest accountRequest) {
        validateAccountRequest(accountRequest);

        Client client = clientRepository.findById(accountRequest.getClientId())
                .orElseThrow(() -> new ServiceException(Error.CLIENT_NOT_FOUND));


        BigDecimal availableBalance = BigDecimal.valueOf(client.getAvailableBalance());
        BigDecimal valorCompra = BigDecimal.valueOf(accountRequest.getValorCompra());

        if (availableBalance.compareTo(valorCompra) < 0) {
            throw new ServiceException(Error.CREDIT_LINE_EXCEEDED);
        }

        Account account = fromRequest(accountRequest, client);
        account = accountRepository.save(account);


        DatosEntrada datosEntrada = fromRequestToDatosEntrada(accountRequest);
        List<DatosSalida> datosSalidaList = CalculadoraGrilla.calculadora(datosEntrada);
        // Asignamos la cuenta guardada a cada instancia de
        final Account finalAccount = account;
        datosSalidaList.forEach(datosSalida -> {
            datosSalida.setAccount(finalAccount);
            datosSalida.setEstado("POR_PAGAR");
        });


        AccountResponse accountResponse = toResponse(datosEntrada, datosSalidaList);
        datosSalidaRepository.saveAll(datosSalidaList);

        client.setAvailableBalance(client.getAvailableBalance() - accountRequest.getValorCompra());
        client.setDebt(client.getDebt() + accountRequest.getValorCompra());
        clientRepository.save(client);
        return accountResponse;
    }

    @Override
    public AccountResponse getById(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ServiceException(Error.ACCOUNT_NOT_FOUND));
        List<DatosSalida> datosSalidaList = datosSalidaRepository.findAllByAccount_Id(id);
        return toResponse(fromAccountToDatosEntrada(account), datosSalidaList);
    }





    /*
    @Override
    public AccountResponse getById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(this::toResponse).orElse(null);
    }

     */

    @Override
    public List<Account> getAll(UUID clientId) {
        if (clientId != null) {
            return accountRepository.findAllByClientId(clientId);
        } else {
            return accountRepository.findAll();
        }
    }


    @Override
    public AccountResponse update(Integer id, AccountRequest accountRequest) {
        return null;
    }


    /*
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

    */

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Double getAccountDebt(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceException(Error.ACCOUNT_NOT_FOUND));
        return 0.0;
    }
    @Override
    public double calculateTotalInteresMoratorio(Integer accountId) {
        List<DatosSalida> datosSalidaList = datosSalidaRepository.findAllByAccount_Id(accountId);
        return datosSalidaList.stream()
                .mapToDouble(DatosSalida::getInteresMora)
                .sum();
    }
    @Override
    public List<DatosSalida> calcularInteresMoratorio(Integer accountId, LocalDate fechaPagoReal) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ServiceException(Error.ACCOUNT_NOT_FOUND));

        List<DatosSalida> datosSalida = datosSalidaRepository.findAllByAccount_Id(accountId);

        for (DatosSalida dato : datosSalida) {
            LocalDate fechaVencimiento = LocalDate.parse(dato.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yy"));
            long diasAtraso = ChronoUnit.DAYS.between(fechaVencimiento, fechaPagoReal);

            if (diasAtraso > 0) {
                double saldoInicial = dato.getSaldoInicial();
                double tasaMoratoria = account.getTasaMoratoria() / 100; // Asumiendo que está en porcentaje
                double interesMora = saldoInicial * (Math.pow(1 + tasaMoratoria, diasAtraso / 30.0) - 1);
                dato.setInteresMora(interesMora);
            } else {
                dato.setInteresMora(0);
            }
        }

        return datosSalidaRepository.saveAll(datosSalida);
    }

    private Account fromRequest(AccountRequest accountRequest, Client client) {
        return Account.builder()
                .valorCompra(accountRequest.getValorCompra())
                .tipoTasa(accountRequest.getTipoTasa())
                .capitalizacionTasa(accountRequest.getCapitalizacionTasa())
                .valorTasa(accountRequest.getValorTasa())
                .tipoCredito(accountRequest.getTipoCredito())
                .numeroCuotas(accountRequest.getNumeroCuotas())
                .plazoGracia(accountRequest.getPlazoGracia())
                .periodoGracia(accountRequest.getPeriodoGracia())
                .paymentDate(accountRequest.getPaymentDate())
                .tasaMoratoria(accountRequest.getTasaMoratoria())
                .diasAtraso(accountRequest.getDiasAtraso())
                .limiteCredito(client.getCreditLine())
                .tiempoTasa(accountRequest.getTiempoTasa())
                .client(client)
                .build();
    }

    private DatosEntrada fromRequestToDatosEntrada(AccountRequest accountRequest) {
        Client client = clientRepository.findById(accountRequest.getClientId())
                .orElseThrow(() -> new ServiceException(Error.CLIENT_NOT_FOUND));

        return DatosEntrada.builder()
                .montoPrestamo(accountRequest.getValorCompra())  // valorCompra -> montoPrestamo
                .tasa(accountRequest.getValorTasa())  // valorTasa -> tasa
                .numeroCuotas(accountRequest.getNumeroCuotas())  // numeroCuotas -> numeroCuotas
                .tipoTasa(accountRequest.getTipoTasa())  // tipoTasa -> tipoTasa
                .capitalizacion(accountRequest.getCapitalizacionTasa())  // capitalizacionTasa -> capitalizacion
                .tipoPeriodoGracia(accountRequest.getPlazoGracia())  // plazoGracia -> tipoPeriodoGracia
                .periodoGraciaMeses(accountRequest.getPeriodoGracia())  // periodoGracia -> periodoGraciaMeses
                .fechaInicial(accountRequest.getPaymentDate())  // paymentDate -> fechaInicial
                .tasaMoratoria(accountRequest.getTasaMoratoria())  // tasaMoratoria -> tasaMoratoria
                .diasAtraso(accountRequest.getDiasAtraso())  // diasAtraso -> diasAtraso
                .limiteCredito(client.getCreditLine())  // limiteCredito -> limiteCredito
                .tiempoTasa(accountRequest.getTiempoTasa())  // tiempoTasa -> tiempoTasa
                .build();
    }

    private AccountResponse toResponse(DatosEntrada datosEntrada, List<DatosSalida> datosSalida) {
        return AccountResponse.builder()
                .datosEntrada(datosEntrada)
                .datosSalidaList(datosSalida)
                .build();
    }


    private void validateAccountRequest(AccountRequest accountRequest) {
        if (accountRequest.getValorCompra() == null) {
            throw new ServiceException(Error.PURCHASE_VALUE_REQUIRED);
        } else if (accountRequest.getValorCompra() <= 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getValorTasa() == null) {
            throw new ServiceException(Error.INTEREST_RATE_REQUIRED);
        } else if (accountRequest.getValorTasa() < 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getNumeroCuotas() == null) {
            throw new ServiceException(Error.INSTALLMENT_COUNT_REQUIRED);
        } else if (accountRequest.getNumeroCuotas() <= 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getPeriodoGracia() == null) {
            throw new ServiceException(Error.GRACE_PERIOD_LENGTH_REQUIRED);
        } else if (accountRequest.getPeriodoGracia() < 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getTipoTasa() == null || accountRequest.getTipoTasa().isEmpty()) {
            throw new ServiceException(Error.INTEREST_TYPE_REQUIRED);
        }

        if (accountRequest.getCapitalizacionTasa() == null || accountRequest.getCapitalizacionTasa() == 0) {
            throw new ServiceException(Error.CAPITALIZATION_PERIOD_REQUIRED);
        }

        if (accountRequest.getTipoCredito() == null || accountRequest.getTipoCredito().isEmpty()) {
            throw new ServiceException(Error.CREDIT_TYPE_REQUIRED);
        }

        if (accountRequest.getPlazoGracia() == null) {
            throw new ServiceException(Error.GRACE_PERIOD_REQUIRED);
        }

        if (accountRequest.getPaymentDate() == null) {
            throw new ServiceException(Error.PAYMENT_DATE_REQUIRED);
        }

        if (accountRequest.getTasaMoratoria() != null && accountRequest.getTasaMoratoria() < 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getDiasAtraso() != null && accountRequest.getDiasAtraso() < 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getTiempoTasa() != null && accountRequest.getTiempoTasa() <= 0) {
            throw new ServiceException(Error.GENERIC_ERROR);
        }

        if (accountRequest.getClientId() == null) {
            throw new ServiceException(Error.CLIENT_NOT_FOUND);
        }
    }


    private DatosEntrada fromAccountToDatosEntrada(Account account) {
        return DatosEntrada.builder()
                .montoPrestamo(account.getValorCompra())  // valorCompra -> montoPrestamo
                .tasa(account.getValorTasa())  // valorTasa -> tasa
                .numeroCuotas(account.getNumeroCuotas())  // numeroCuotas -> numeroCuotas
                .tipoTasa(account.getTipoTasa())  // tipoTasa -> tipoTasa
                .capitalizacion(account.getCapitalizacionTasa())  // capitalizacionTasa -> capitalizacion
                .tipoPeriodoGracia(account.getPlazoGracia())  // plazoGracia -> tipoPeriodoGracia
                .periodoGraciaMeses(account.getPeriodoGracia())  // periodoGracia -> periodoGraciaMeses
                .fechaInicial(account.getPaymentDate())  // paymentDate -> fechaInicial
                .tasaMoratoria(account.getTasaMoratoria())  // tasaMoratoria -> tasaMoratoria
                .diasAtraso(account.getDiasAtraso())  // diasAtraso -> diasAtraso
                .limiteCredito(account.getClient().getCreditLine())  // limiteCredito -> limiteCredito
                .tiempoTasa(account.getTiempoTasa())  // tiempoTasa -> tiempoTasa
                .build();
    }


}