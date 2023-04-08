package io.github.vahansahakyan.CodeInspect.repository;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
  Set<Assignment> findByUser(User user);

  @Query("select a from Assignment a where a.status = 'submitted'")
  Set<Assignment> findByCodeReviewer(User user);
}
