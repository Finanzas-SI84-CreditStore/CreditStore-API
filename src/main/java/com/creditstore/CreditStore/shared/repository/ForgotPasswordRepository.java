package com.creditstore.CreditStore.shared.repository;

import com.creditstore.CreditStore.security.entity.User;
import com.creditstore.CreditStore.shared.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
  ForgotPassword findByOtpAndUser(Integer otp, User user);

  ForgotPassword findByUser(User user);

}
