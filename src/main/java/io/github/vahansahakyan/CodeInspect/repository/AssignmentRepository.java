package io.github.vahansahakyan.CodeInspect.repository;

import java.util.Set;

import io.github.vahansahakyan.CodeInspect.enums.AssignmentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import org.springframework.data.jpa.repository.Query;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
  Set<Assignment> findByUser(User user);
    @Query("select a from Assignment a where a.status = 'submitted'")
    Set<Assignment> findByCodeReviewer(User user);
}
