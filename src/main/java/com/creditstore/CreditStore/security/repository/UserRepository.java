package com.creditstore.CreditStore.security.repository;

import com.creditstore.CreditStore.security.entity.User;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

  User findByEmail(String email);

  void deleteById(UUID id);

  boolean existsByEmail(String email);

  boolean existsByDni(String dni);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.password = ?2 WHERE u.email=?1")
  void updatePassword(String email, String password);

}
