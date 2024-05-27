package com.creditstore.CreditStore.security.rest;
import com.creditstore.CreditStore.security.model.LoginRequest;
import com.creditstore.CreditStore.security.model.LoginResponse;
import com.creditstore.CreditStore.security.model.UserReq;
import com.creditstore.CreditStore.security.repository.UserRepository;
import com.creditstore.CreditStore.security.services.UserService;
import com.creditstore.CreditStore.util.exception.InvalidDataException;
import com.creditstore.CreditStore.util.util.Error;
import jakarta.validation.Valid;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserRest {

  private static final Logger logger = LoggerFactory.getLogger(UserRest.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @PostMapping
  public UUID create(@Valid @RequestBody UserReq userReq, BindingResult result){
    logger.debug("Creando usuario con {}", userReq);
    if(result.hasErrors()){
      throw new InvalidDataException(result, Error.INVALID_USER_DATA);
    }
    return userService.create(userReq).getId();
  }

  @PostMapping("/login")
  public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
    if (result.hasErrors()) {
      throw new InvalidDataException(result, Error.INVALID_USER_DATA);
    }
    return userService.authenticate(loginRequest);
  }
}
