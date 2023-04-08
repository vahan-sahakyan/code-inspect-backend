package io.github.vahansahakyan.CodeInspect.service;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.enums.AssignmentStatusEnum;
import io.github.vahansahakyan.CodeInspect.enums.AuthorityEnum;
import io.github.vahansahakyan.CodeInspect.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AssignmentService {

  @Autowired
  private AssignmentRepository assignmentRepo;

  public Assignment save(User user) {
    Assignment assignment = new Assignment();
    assignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION.getStatus());
    assignment.setNumber(findNextAssignmentToSubmit(user));
    assignment.setUser(user);
    System.out.println("/api/auth");
    return assignmentRepo.save(assignment);
  }

  private Integer findNextAssignmentToSubmit(User user) {
    Set<Assignment> assignmentsByUser = assignmentRepo.findByUser(user);
    if (assignmentsByUser == null) {
      return 1;
    }
    Optional<Integer> nextAssignmentNumOpt = assignmentsByUser.stream()
        .sorted((a1, a2) -> {
          if (a1.getNumber() == null) return 1;
          if (a2.getNumber() == null) return 1;
          return a2.getNumber().compareTo(a1.getNumber());
        })
        .map(assignment -> {
          if (assignment.getNumber() == null) return 1;
          return assignment.getNumber() + 1;
        })
        .findFirst();

    return nextAssignmentNumOpt.orElse(1);
  }

  public Set<Assignment> findByUser(User user) {
    boolean hasCodeReviewerRole = user.getAuthorities()
        .stream()
        .filter(auth -> AuthorityEnum.ROLE_CODE_REVIEWER.name().equals(auth.getAuthority()))
        .count() > 0;
    if (hasCodeReviewerRole) {
      // load assignments if you're a code reviewer role
      return assignmentRepo.findByCodeReviewer(user);
    } else {
      // load assignments if you're a student role
      return assignmentRepo.findByUser(user);
    }
  }

  public Optional<Assignment> findById(Long assignmentId) {

    return assignmentRepo.findById(assignmentId);
  }

  public Assignment save(Assignment assignment) {
    return assignmentRepo.save(assignment);
  }

}
