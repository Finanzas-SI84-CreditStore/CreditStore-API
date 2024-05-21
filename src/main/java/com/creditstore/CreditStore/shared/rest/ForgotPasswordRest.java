package com.creditstore.CreditStore.shared.rest;

import com.creditstore.CreditStore.shared.email.model.ChangePasswordReq;
import com.creditstore.CreditStore.shared.service.ForgotPasswordService;
import com.creditstore.CreditStore.util.exception.InvalidDataException;
import com.creditstore.CreditStore.util.util.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordRest {

  private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordRest.class);

  @Autowired
  ForgotPasswordService forgotPasswordService;

  @PostMapping("verify-mail/{email}")
  public ResponseEntity<String> verifyEmail(@PathVariable String email){
    return forgotPasswordService.verifyEmail(email);
  }

  @PostMapping("verify/{otp}/{email}")
  public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email){
    return forgotPasswordService.verifyOtp(otp, email);
  }

  @PostMapping("change-password/{email}")
  public ResponseEntity<String> changePassword(@PathVariable String email,
      @RequestBody ChangePasswordReq changePasswordReq, BindingResult result){
    logger.debug("Actualizando contraseña");
    if(result.hasErrors()){
      throw new InvalidDataException(result, Error.INVALID_PASSWORD);
    }
    return forgotPasswordService.changePassword(changePasswordReq, email);
  }
}
