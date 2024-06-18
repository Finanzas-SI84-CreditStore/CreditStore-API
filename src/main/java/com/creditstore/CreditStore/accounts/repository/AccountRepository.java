package com.creditstore.CreditStore.accounts.repository;

import com.creditstore.CreditStore.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByClientId(UUID clientId);
}
