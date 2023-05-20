package io.github.vahansahakyan.CodeInspect.repository;

import io.github.vahansahakyan.CodeInspect.domain.Comment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
  
}
