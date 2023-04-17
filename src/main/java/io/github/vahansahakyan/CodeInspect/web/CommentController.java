package io.github.vahansahakyan.CodeInspect.web;

import io.github.vahansahakyan.CodeInspect.domain.Comment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.dto.CommentDto;
import io.github.vahansahakyan.CodeInspect.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping("")
  public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal User user) {
    Comment comment = commentService.save(commentDto, user);
    return ResponseEntity.ok(comment);
  }

  @GetMapping("")
  public ResponseEntity<Set<Comment>> getCommentsByAssignment(@RequestParam Long assignmentId) {
    Set<Comment> comments = commentService.getCommentsByAssignment(assignmentId);
    return ResponseEntity.ok(comments);
  }
}
