package com.creditstore.CreditStore.clients.service;

import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.clients.model.ClientDto;
import com.creditstore.CreditStore.clients.model.ClientReq;
import java.util.List;
import java.util.UUID;

public interface ClientService {

  Client create(ClientReq client, UUID userId);

  List<Client> getAllClientsByUserId(UUID userId);

  Client getById(UUID id);

  void delete(UUID id);

  List<Client> getAllBiggestDebtorsByUserId(UUID userId);


}
