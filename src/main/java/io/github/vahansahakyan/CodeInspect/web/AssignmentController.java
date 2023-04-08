package io.github.vahansahakyan.CodeInspect.web;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.dto.AssignmentResponseDto;
import io.github.vahansahakyan.CodeInspect.enums.AuthorityEnum;
import io.github.vahansahakyan.CodeInspect.service.AssignmentService;
import io.github.vahansahakyan.CodeInspect.service.UserService;
import io.github.vahansahakyan.CodeInspect.util.AuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
  @Autowired
  private AssignmentService assignmentService;
  @Autowired
  private UserService userService;

  @PostMapping("")
  public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user) {
    Assignment newAssignment = assignmentService.save(user);
    return ResponseEntity.ok(newAssignment);
  }

  @GetMapping("")
  public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user) {
    Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
    return ResponseEntity.ok(assignmentsByUser);
  }

  @GetMapping("{assignmentId}")
  public ResponseEntity<?> getAssignment(@PathVariable Long assignmentId, @AuthenticationPrincipal User user) {
    Optional<Assignment> assignmentOpt = assignmentService.findById(assignmentId);
    return ResponseEntity.ok(new AssignmentResponseDto(assignmentOpt.orElse(new Assignment())));
  }

  @PutMapping("{assignmentId}")
  public ResponseEntity<?> updateAssignment(@PathVariable Long assignmentId, @RequestBody Assignment assignment,
                                            @AuthenticationPrincipal User user) {
    // add the code reviewer to this assignment if it was claimed
    if (assignment.getCodeReviewer() != null) {
      User codeReviewer = assignment.getCodeReviewer();
      codeReviewer = userService.findUserByUsername(codeReviewer.getUsername()).orElse(new User());

      if (AuthorityUtil.hasRole(AuthorityEnum.ROLE_CODE_REVIEWER.name(), codeReviewer)) {
        assignment.setCodeReviewer(codeReviewer);
      }
    }
    Assignment updatedAssignment = assignmentService.save(assignment);
    return ResponseEntity.ok(updatedAssignment);
  }

}
