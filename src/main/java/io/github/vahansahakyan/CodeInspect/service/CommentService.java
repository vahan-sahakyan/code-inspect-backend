package io.github.vahansahakyan.CodeInspect.service;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.domain.Comment;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.dto.CommentDto;
import io.github.vahansahakyan.CodeInspect.repository.AssignmentRepository;
import io.github.vahansahakyan.CodeInspect.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Set;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepo;
  @Autowired
  private AssignmentRepository assignmentRepo;

  @Autowired
  private DbChangeService dbChangesService;

  public Comment save(CommentDto commentDto, User user) {
    Comment comment = new Comment();

    Assignment assignment = assignmentRepo.getById(commentDto.getAssignmentId());

    comment.setId(commentDto.getId());
    comment.setAssignment(assignment);
    comment.setText(commentDto.getText());
    comment.setCreatedBy(user);

    comment.setCreatedDate(LocalDateTime.now());

    dbChangesService.processDbChange(
        "CREATE COMMENT { assignment: "
            + commentDto.getAssignmentId() + " } -> "
            + (commentDto.getText().isEmpty() ? "EMPTY COMMENT" : commentDto.getText()));

    return commentRepo.save(comment);
  }

  public Set<Comment> getCommentsByAssignment(Long assignmentId) {
    Set<Comment> comments = commentRepo.findByAssignmentId(assignmentId);
    return comments;
  }

  public void delete(Long commentId) {
    dbChangesService.processDbChange("DELETE COMMENT " + commentId);
    commentRepo.deleteById(commentId);
  }
}
