package io.github.vahansahakyan.CodeInspect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.vahansahakyan.CodeInspect.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

}
