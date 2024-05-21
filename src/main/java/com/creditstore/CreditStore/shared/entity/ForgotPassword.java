package com.creditstore.CreditStore.shared.entity;

import com.creditstore.CreditStore.security.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ForgotPassword {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer fpid;

  @Column(nullable = false)
  private Integer otp;

  @Column(nullable = false)
  private Date expirationTime;

  @OneToOne
  private User user;

}
