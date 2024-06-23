package com.creditstore.CreditStore.accounts.rest;

import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.accounts.model.AccountRequest;
import com.creditstore.CreditStore.accounts.model.AccountResponse;
import com.creditstore.CreditStore.accounts.model.PayRequest;
import com.creditstore.CreditStore.accounts.model.PayResponse;
import com.creditstore.CreditStore.accounts.service.AccountService;
import com.creditstore.CreditStore.accounts.service.PayService;
import com.creditstore.CreditStore.clients.model.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountRest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PayService payService;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest accountRequest) {
        AccountResponse newAccount = accountService.create(accountRequest);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/clients/{clientId}/accounts")
    public ResponseEntity<List<Account>> getAllByClientId(@PathVariable UUID clientId) {
        List<Account> accounts = accountService.getAll(clientId);
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Integer id, @RequestBody AccountRequest accountRequest) {
        AccountResponse updatedAccount = accountService.update(id, accountRequest);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/pays")
    public ResponseEntity<PayResponse> createPay(@PathVariable Integer id, @RequestBody PayRequest payRequest) {
        PayResponse newPay = payService.create(payRequest, id);
        return ResponseEntity.ok(newPay);
    }

    @GetMapping("/{id}/pays")
    public ResponseEntity<List<PayResponse>> getAllPays(@PathVariable Integer id) {
        List<PayResponse> pays = payService.getAllByAccountId(id);
        return ResponseEntity.ok(pays);
    }

    // Nuevo endpoint para obtener la deuda del cliente
    @GetMapping("/{id}/client-debt")
    public ResponseEntity<ClientDto> getClientDebt(@PathVariable Integer id) {
        ClientDto clientDebt = accountService.getClientDebt(id);
        return ResponseEntity.ok(clientDebt);
    }
}
