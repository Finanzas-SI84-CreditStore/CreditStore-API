package com.creditstore.CreditStore.shared.email.services;

import com.creditstore.CreditStore.shared.email.model.EmailReq;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  @Autowired
  public JavaMailSender emailSender;

  @Override
  public void sendMessage(EmailReq emailReq) throws MessagingException {
    MimeMessage mimeMessage = emailSender.createMimeMessage();
    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

    String sender = "creditstorepe@gmail.com";
    message.setFrom(sender);
    message.setTo(emailReq.getRecipients());
    message.setSubject(emailReq.getSubject());
    message.setText(emailReq.getMessage(), true);

    emailSender.send(mimeMessage);
  }
}
