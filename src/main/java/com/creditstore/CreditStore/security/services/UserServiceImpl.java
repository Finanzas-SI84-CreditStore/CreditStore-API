package com.creditstore.CreditStore.security.services;

import com.creditstore.CreditStore.security.entity.User;
import com.creditstore.CreditStore.security.model.UserReq;
import com.creditstore.CreditStore.security.repository.UserRepository;
import com.creditstore.CreditStore.util.exception.ServiceException;
import com.creditstore.CreditStore.util.util.Error;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public User create(UserReq userReq) {
    User user = fromReq(userReq, null);
    if(userRepository.existsByEmail(user.getEmail())){
      throw new ServiceException(Error.USER_ALREADY_EXIST);
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
    user.setNames(userReq.getNames());
    user.setLastNames(userReq.getLastNames());
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

}
