package io.github.vahansahakyan.CodeInspect.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
  public Set<Assignment> findByUser(User user);

}
