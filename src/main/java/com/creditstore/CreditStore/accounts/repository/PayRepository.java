package com.creditstore.CreditStore.accounts.repository;

import com.creditstore.CreditStore.accounts.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<Pay, Integer> {

    List<Pay> findAllByAccountId(Integer accountId);
}
