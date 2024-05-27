package com.creditstore.CreditStore.security.services;

import com.creditstore.CreditStore.security.model.LoginRequest;
import com.creditstore.CreditStore.security.model.LoginResponse;
import com.creditstore.CreditStore.security.entity.User;
import com.creditstore.CreditStore.security.model.UserReq;

import java.util.UUID;

public interface UserService {
  User create(UserReq userReq);

  User update(UserReq userReq);

  User getById(UUID id);

  void delete(UUID id);

  LoginResponse authenticate(LoginRequest loginRequest);
}
