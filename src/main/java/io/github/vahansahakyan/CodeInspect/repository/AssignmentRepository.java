package io.github.vahansahakyan.CodeInspect.repository;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
  Set<Assignment> findByUser(User user);

  @Query("SELECT a FROM Assignment a "
      + "WHERE (a.status = 'submitted'  AND (a.codeReviewer IS NULL OR a.codeReviewer = :codeReviewer))"
      + "OR a.codeReviewer = :codeReviewer")
  Set<Assignment> findByCodeReviewer(User codeReviewer);
}
