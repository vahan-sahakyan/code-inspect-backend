package io.github.vahansahakyan.CodeInspect.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {
    ASSIGNMENT_1(1, "Build a To-Do List App (React + Go)"),
    ASSIGNMENT_2(2, "Create a RESTful API (Go/Java)"),
    ASSIGNMENT_3(3, "Write Unit Tests (Jest, Mocha, JUnit)"),
    ASSIGNMENT_4(4, "Generate SQL Reports (PostgreSQL/MySQL)"),
    ASSIGNMENT_5(5, "Develop a Real-Time Chat App (React/Go)"),
    ASSIGNMENT_6(6, "Implement Multi-Threading (Go/Java)"),
    ASSIGNMENT_7(7, "Build a CRUD Application (Spring MVC)"),
    ASSIGNMENT_8(8, "Create a Simple Blog API (Go/Java)"),
    ASSIGNMENT_9(9, "Deploy an App to GCP"),
    ASSIGNMENT_10(10, "Integrate a Database (SQL/NoSQL)"),
    ASSIGNMENT_11(11, "Build a Personal Portfolio (React)"),
    ASSIGNMENT_12(12, "Build a Weather App (JavaScript/React)"),
    ASSIGNMENT_13(13, "Create a Movie Search App (React + API Integration)"),
    ASSIGNMENT_14(14, "Build a Simple E-commerce Site (React + Go)");

    private final int assignmentNum;
    private final String assignmentName;

    AssignmentEnum(int assignmentNum, String assignmentName) {
        this.assignmentNum = assignmentNum;
        this.assignmentName = assignmentName;
    }

    public int getAssignmentNum() {
        return assignmentNum;
    }

    public String getAssignmentName() {
        return assignmentName;
    }
}