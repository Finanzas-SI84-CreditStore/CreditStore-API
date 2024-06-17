package com.creditstore.CreditStore.accounts.service;

import com.creditstore.CreditStore.accounts.model.PayRequest;
import com.creditstore.CreditStore.accounts.model.PayResponse;

import java.util.List;

public interface PayService {

    PayResponse create(PayRequest payRequest, Integer accountId);
    PayResponse getById(Integer id);
    List<PayResponse> getAllByAccountId(Integer accountId);
    PayResponse update(Integer id, PayRequest payRequest);
}
