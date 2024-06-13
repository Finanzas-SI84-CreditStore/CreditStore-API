package com.creditstore.CreditStore.shared.service;

import com.creditstore.CreditStore.security.entity.User;
import com.creditstore.CreditStore.security.repository.UserRepository;
import com.creditstore.CreditStore.shared.email.model.ChangePasswordReq;
import com.creditstore.CreditStore.shared.email.model.EmailReq;
import com.creditstore.CreditStore.shared.email.services.EmailService;
import com.creditstore.CreditStore.shared.email.util.EmailTemplate;
import com.creditstore.CreditStore.shared.entity.ForgotPassword;
import com.creditstore.CreditStore.shared.repository.ForgotPasswordRepository;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;
import jakarta.mail.MessagingException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{

  @Autowired
  UserRepository userRepository;

  @Autowired
  EmailService emailService;

  @Autowired
  ForgotPasswordRepository forgotPasswordRepository;

  @Override
  public ResponseEntity<String> verifyEmail(String email) {
    String[] recipientsArray = new String[]{email};
    int otp = otpGenerator();
    if(!userRepository.existsByEmail(email)){
      throw new ServiceException(Error.INVALID_EMAIL);
    }
    EmailReq emailReq = EmailReq.builder()
        .recipients(recipientsArray)
        .subject("CreditStore - Cambio de contraseña")
        .message(EmailTemplate.generateCodeOtpBody(String.valueOf(otp)))
        .build();

    User user = userRepository.findByEmail(email);
    ForgotPassword forgotPassword = forgotPasswordRepository.findByUser(user);
    if(forgotPassword!=null){
      forgotPassword.setOtp(otp);
      forgotPassword.setExpirationTime(new Date(System.currentTimeMillis() + 2*60*1000));
    }else {
      forgotPassword = ForgotPassword.builder()
          .otp(otp)
          .expirationTime(new Date(System.currentTimeMillis() + 2*60*1000))
          .user(userRepository.findByEmail(email))
          .build();
    }

    try {
      emailService.sendMessage(emailReq);
    } catch (MessagingException e) {
      throw new ServiceException(Error.GENERIC_ERROR);
    }
    forgotPasswordRepository.save(forgotPassword);
    return new ResponseEntity<>("Se envió la verificación a su correo con éxito", HttpStatus.OK);

  }

  @Override
  public ResponseEntity<String> verifyOtp(Integer otp, String email) {
    User user = userRepository.findByEmail(email);
    if(user== null){
      throw new ServiceException(Error.INVALID_EMAIL);
    }

    ForgotPassword forgotPassword = forgotPasswordRepository.findByOtpAndUser(otp, user);
    if(forgotPassword== null){
      throw new ServiceException(Error.INVALID_OTP);
    }

    if(forgotPassword.getExpirationTime().before(Date.from(Instant.now()))){
      forgotPasswordRepository.deleteById(forgotPassword.getFpid());
      return new ResponseEntity<>("OTP ha expirado", HttpStatus.EXPECTATION_FAILED);
    }

    return new ResponseEntity<>("OTP verificado", HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> changePassword(ChangePasswordReq changePasswordReq, String email) {
    if(!Objects.equals(changePasswordReq.getPassword(), changePasswordReq.getNewPassword())){
      return new ResponseEntity<>("Por favor, verifique la coincidencia de sus contraseñas", HttpStatus.EXPECTATION_FAILED);
    }
    //Se debería usar un codificador de la contraseña
    userRepository.updatePassword(email, changePasswordReq.getPassword());
    return new ResponseEntity<>("La contraseña se ha actualizado", HttpStatus.OK);
  }

  private Integer otpGenerator(){
    Random random = new Random();
    return random.nextInt(100_000, 999_999);
  }

}
