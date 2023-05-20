package io.github.vahansahakyan.CodeInspect.service;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.Comment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.dto.CommentDto;
import io.github.vahansahakyan.CodeInspect.dto.SignUpRequest;
import io.github.vahansahakyan.CodeInspect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepo;

  public Optional<User> findUserByUsername(String username) {
    return userRepo.findByUsername(username);
  }

}
