package com.creditstore.CreditStore.accounts.rest;

import com.creditstore.CreditStore.accounts.model.AccountRequest;
import com.creditstore.CreditStore.accounts.model.AccountResponse;
import com.creditstore.CreditStore.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRest {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest accountRequest) {
        AccountResponse newAccount = accountService.create(accountRequest);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Integer id) {
        AccountResponse account = accountService.getById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        List<AccountResponse> accounts = accountService.getAll();
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
}
