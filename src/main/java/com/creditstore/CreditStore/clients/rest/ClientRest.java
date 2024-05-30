package com.creditstore.CreditStore.clients.rest;

import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.clients.model.ClientDto;
import com.creditstore.CreditStore.clients.model.ClientReq;
import com.creditstore.CreditStore.clients.repository.ClientRepository;
import com.creditstore.CreditStore.clients.service.ClientService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class ClientRest {
  @Autowired
  ClientRepository clientRepository;

  @Autowired
  ClientService clientService;

  @GetMapping("/users/{userId}/clients")
  public List<Client> getAllClientsByUser(@PathVariable("userId") UUID userId) {
    return clientService.getAllClientsByUserId(userId);
  }

  @PostMapping("/users/{userId}/clients")
    public UUID create(@PathVariable("userId") UUID userId, @RequestBody ClientReq clientReq) {
        return clientService.create(clientReq, userId).getId();
  }
  @GetMapping("/users/{userId}/clients/debtors")
  public List<Client> getAllBiggestDebtorsByUser(@PathVariable("userId") UUID userId) {
    return clientService.getAllBiggestDebtorsByUserId(userId);
  }

}
