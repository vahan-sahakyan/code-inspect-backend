package io.github.vahansahakyan.CodeInspect.service;

import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepo;

  public Optional<User> findUserByUsername(String username) {
    return userRepo.findByUsername(username);
  }

}
