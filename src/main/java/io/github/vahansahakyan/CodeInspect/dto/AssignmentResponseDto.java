package io.github.vahansahakyan.CodeInspect.dto;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.enums.AssignmentEnum;
import io.github.vahansahakyan.CodeInspect.enums.AssignmentStatusEnum;

public class AssignmentResponseDto {
  private final AssignmentEnum[] assignmentEnum = AssignmentEnum.values();
  private final AssignmentStatusEnum[] assignmentStatusEnum = AssignmentStatusEnum.values();
  private Assignment assignment;


  public AssignmentResponseDto(Assignment assignment) {
    super();
    this.assignment = assignment;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  public AssignmentEnum[] getAssignmentEnum() {
    return assignmentEnum;
  }

  public AssignmentStatusEnum[] getAssignmentStatusEnum() {
    return assignmentStatusEnum;
  }
}
