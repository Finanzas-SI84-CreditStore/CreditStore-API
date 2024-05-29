package com.creditstore.CreditStore.security.entity;

import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.util.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String names;

  private String lastNames;

  private String password;

  private String email;

  private String dni;

  private LocalDate birthDate;

  private String storeName;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Client> clients = new ArrayList<>();

}
