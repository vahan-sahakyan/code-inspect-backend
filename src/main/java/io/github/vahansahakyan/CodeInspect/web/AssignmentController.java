package io.github.vahansahakyan.CodeInspect.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.service.AssigmentService;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin("http://localhost:5173/")
public class AssignmentController {
  @Autowired
  private AssigmentService assignmentService;

  @PostMapping("")
  @CrossOrigin("http://localhost:5173/")
  public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user) {
    Assignment newAssignment = assignmentService.save(user);
    return ResponseEntity.ok(newAssignment);
  }

  @GetMapping("")
  @CrossOrigin("http://localhost:5173/")
  public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user) {
    Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
    return ResponseEntity.ok(assignmentsByUser);
  }

  @GetMapping("{assignmentId}")
  @CrossOrigin("http://localhost:5173/")
  public ResponseEntity<?> getAssignment(@PathVariable Long assignmentId, @AuthenticationPrincipal User user) {
    Optional<Assignment> assignmentOpt = assignmentService.findById(assignmentId);
    return ResponseEntity.ok(assignmentOpt.orElse(new Assignment()));
  }

  @PutMapping("{assignmentId}")
  @CrossOrigin("http://localhost:5173/")
  public ResponseEntity<?> updateAssignment(@PathVariable Long assignmentId, @RequestBody Assignment assignment,
      @AuthenticationPrincipal User user) {
    Assignment updatedAssignment = assignmentService.save(assignment);
    return ResponseEntity.ok(updatedAssignment);
  }

}
