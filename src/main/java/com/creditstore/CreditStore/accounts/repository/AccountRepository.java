package com.creditstore.CreditStore.accounts.repository;

import com.creditstore.CreditStore.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByClientId(UUID clientId);

}
