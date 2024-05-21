package com.creditstore.CreditStore.shared.email.services;

import com.creditstore.CreditStore.shared.email.model.EmailReq;
import jakarta.mail.MessagingException;

public interface EmailService {
  void sendMessage(EmailReq emailReq) throws MessagingException;

}
