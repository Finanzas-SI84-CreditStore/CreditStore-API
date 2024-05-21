package com.creditstore.CreditStore.shared.service;

import com.creditstore.CreditStore.shared.email.model.ChangePasswordReq;
import org.springframework.http.ResponseEntity;

public interface ForgotPasswordService {

  ResponseEntity<String> verifyEmail(String email);

  ResponseEntity<String> verifyOtp(Integer otp, String email);

  ResponseEntity<String> changePassword(ChangePasswordReq changePasswordReq, String email);

}
