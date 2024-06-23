package com.creditstore.CreditStore.clients.service;

import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.clients.model.ClientReq;
import com.creditstore.CreditStore.clients.repository.ClientRepository;
import com.creditstore.CreditStore.security.entity.User;
import com.creditstore.CreditStore.security.repository.UserRepository;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public Client create(ClientReq clientReq, UUID userId) {
    Client client = fromReq(clientReq, null);
    if (clientRepository.existsByDni(client.getDni())) {
      throw new ServiceException(Error.EXIST_CLIENT);
    }

    LocalDate now = LocalDate.now();
    LocalDate eighteenYearsAgo = now.minusYears(18);
    if (client.getBirthDate().isAfter(eighteenYearsAgo)) {
      throw new ServiceException(Error.INVALID_BIRTH_DATE);
    }

    if (!isDniValid(client.getDni())) {
      throw new ServiceException(Error.INVALID_DNI);
    }

    User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(Error.USER_NOT_FOUND));
    client.setAvailableBalance(client.getCreditLine()-client.getDebt());
    client.setUser(user);
    client.setStatus(1);
    client.setDateHourCreation(new Date());
    client.setCreatedBy(user.getId());
    return clientRepository.save(client);
  }

  @Override
  public List<Client> getAllClientsByUserId(UUID userId) {
    return clientRepository.findAllByUserId(userId);
  }

  @Override
  public Client getById(UUID id) {
    return clientRepository.findById(id).orElseThrow(() -> new ServiceException(Error.CLIENT_NOT_EXISTS));
  }

  @Override
  public void delete(UUID id) {
    Client client = clientRepository.findById(id).orElseThrow(() -> new ServiceException(Error.CLIENT_NOT_FOUND));
    client.setStatus(0);
    clientRepository.save(client);
  }

  @Override
  public List<Client> getAllBiggestDebtorsByUserId(UUID userId) {
    List<Client> clients = clientRepository.findAllByUserId(userId);
    clients.sort((c1, c2) -> Double.compare(c2.getDebt(), c1.getDebt()));
    if (clients.size() > 6) {
      return clients.subList(0, 6);
    }
    return clients;
  }

  private Client fromReq(ClientReq clientReq, UUID id) {
    Client client = new Client();
    if (id != null) {
      client = getById(id);
    }

    client.setName(clientReq.getName());
    client.setLastName(clientReq.getLastName());
    client.setDni(clientReq.getDni());
    client.setBirthDate(clientReq.getBirthDate());
    client.setAddress(clientReq.getAddress());
    client.setCreditLine(clientReq.getCreditLine());

    return client;
  }

  boolean isDniValid(String dni) {
    String dniPattern = "\\d{8}";
    return dni.matches(dniPattern);
  }
}
