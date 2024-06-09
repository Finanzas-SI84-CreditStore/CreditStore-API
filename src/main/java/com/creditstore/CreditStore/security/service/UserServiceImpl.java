package com.creditstore.CreditStore.security.service;

import com.creditstore.CreditStore.security.entity.User;
import com.creditstore.CreditStore.security.model.LoginRequest;
import com.creditstore.CreditStore.security.model.LoginResponse;
import com.creditstore.CreditStore.security.model.UserReq;
import com.creditstore.CreditStore.security.repository.UserRepository;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import jakarta.validation.constraints.AssertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public User create(UserReq userReq) {
    User user = fromReq(userReq, null);

    LocalDate now = LocalDate.now();
    LocalDate eighteenYearsAgo = now.minusYears(18);
    if(user.getBirthDate().isAfter(eighteenYearsAgo)){
      throw new ServiceException(Error.INVALID_BIRTH_DATE);
    }

    if(!isDniValid(user.getDni())){
      throw new ServiceException(Error.INVALID_DNI);
    }

    if(userRepository.existsByDni(user.getDni())){
      throw new ServiceException(Error.USER_ALREADY_EXIST);
    }

    if(userRepository.existsByEmail(user.getEmail())){
      throw new ServiceException(Error.EMAIL_ALREADY_EXIST);
    }

    if(!isPasswordValid(user.getPassword())){
      throw new ServiceException(Error.INVALID_PASSWORD);
    }


    user.setStatus(1);
    user.setDateHourCreation(new Date());
    UUID uuid = UUID.randomUUID();
    user.setCreatedBy(uuid);
    return userRepository.save(user);
  }

  @Override
  public User update(UserReq userReq) {
    return null;
  }

  @Override
  public User getById(UUID id) {
    Optional<User> user = userRepository.findById(id);
    if(user.isEmpty()) {
      throw new ServiceException(Error.USER_NOT_EXISTS);
    }
    return user.get();
  }

  @Override
  public void delete(UUID id) {
    Optional<User> user = userRepository.findById(id);
    if(user.isEmpty()){
      throw new ServiceException(Error.USER_NOT_FOUND);
    }
    user.get().setStatus(0);
    userRepository.save(user.get());
  }

  private User fromReq(UserReq userReq, UUID id){
    User user = new User();
    if(id!= null){
      user = getById(id);
    }
    user.setNames(userReq.getName());
    user.setLastNames(userReq.getLastName());
    user.setDni(userReq.getDni());
    user.setEmail(userReq.getEmail());
    user.setPassword(userReq.getPassword());
    user.setBirthDate(userReq.getBirthDate());
    user.setStoreName(userReq.getStoreName());
    return user;
  }

  private boolean isPasswordValid(String password) {
    Pattern lowerCase = Pattern.compile(".*[a-z].*");
    Pattern upperCase = Pattern.compile(".*[A-Z].*");
    Pattern numbers = Pattern.compile(".*[0-9].*");
    boolean hasMinLarge = password.length() >= 8;

    return lowerCase.matcher(password).matches()
        && upperCase.matcher(password).matches()
        && numbers.matcher(password).matches()
        && hasMinLarge;
  }

  @Override
  public UUID authenticate(LoginRequest loginRequest) {
    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
      throw new ServiceException(Error.INVALID_LOGIN);
    }

    //TODO: :C Aqui pondre el codigo para el JWT
    String token = generateToken(user);
    LoginResponse response = new LoginResponse();
    response.setToken(token);
    response.setMessage("Login exitoso");

    return user.getId();
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  private String generateToken(User user) {
    return "dummy-token"; //
  }

  boolean isDniValid(String dni){
    String dniPattern = "\\d{8}";
    return dni.matches(dniPattern);
  }

}
