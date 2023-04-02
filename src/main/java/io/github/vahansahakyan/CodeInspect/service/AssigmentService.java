package io.github.vahansahakyan.CodeInspect.service;

import java.util.Optional;
import java.util.Set;

import io.github.vahansahakyan.CodeInspect.enums.AssignmentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.repository.AssignmentRepository;

@Service
public class AssigmentService {

  @Autowired
  private AssignmentRepository assignmentRepo;

  public Assignment save(User user) {
    Assignment assignment = new Assignment();
    assignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION.getStatus());
    assignment.setUser(user);
    System.out.println("/api/auth");
    return assignmentRepo.save(assignment);
  }

  public Set<Assignment> findByUser(User user) {
    return assignmentRepo.findByUser(user);
  }

  public Optional<Assignment> findById(Long assignmentId) {

    return assignmentRepo.findById(assignmentId);
  }

  public Assignment save(Assignment assignment) {
    return assignmentRepo.save(assignment);
  }

}
