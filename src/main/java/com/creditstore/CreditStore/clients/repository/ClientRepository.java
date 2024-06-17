package com.creditstore.CreditStore.clients.repository;

import com.creditstore.CreditStore.clients.entity.Client;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, UUID> {

  boolean existsByDni(String dni);

  List<Client> findAllByUserId(UUID userId);


}
