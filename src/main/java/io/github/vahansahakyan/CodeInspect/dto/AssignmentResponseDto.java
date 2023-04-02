package io.github.vahansahakyan.CodeInspect.dto;

import io.github.vahansahakyan.CodeInspect.domain.Assignment;
import io.github.vahansahakyan.CodeInspect.enums.AssignmentEnum;
import io.github.vahansahakyan.CodeInspect.enums.AssignmentStatusEnum;

public class AssignmentResponseDto {
    private Assignment assignment;
    private AssignmentEnum[] assignmentEnum = AssignmentEnum.values();
    private AssignmentStatusEnum[] assignmentStatusEnum = AssignmentStatusEnum.values();


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
